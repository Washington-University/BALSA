package balsa

import grails.gorm.transactions.Transactional
import balsa.file.FileMetadata
import balsa.file.SceneFile
import balsa.scene.Scene

class StatsService {
	
	def getDownloadStats(int max, Dataset dataset) {
		[
			totalDownloads: totalDownloads(dataset), 
			mostRecentDownload: mostRecentDownload(dataset), 
			totalDownloadSize: totalDownloadSize(dataset), 
			uniqueUsers: uniqueUsers(dataset),
			popularDatasets: popularDatasets(max, dataset), 
			popularScenes: popularScenes(max, dataset)
		]
	}
	
	def totalDownloads(Dataset dataset) {
		if (dataset) {
			return dataset.downloads.size()
		}
		Download.count()
	}
	
	def mostRecentDownload(Dataset datasetInstance) {
		def c = Download.createCriteria()
		def results = c.list() {
			dataset {
				if (datasetInstance) {
					eq("id", datasetInstance.id)
				}
			}
			projections {
				max("date")
			}
		}
		results[0]
	}

    def totalDownloadSize(Dataset datasetInstance) {
		def c = Download.createCriteria()
		def results = c.list() {
			dataset {
				if (datasetInstance) {
					eq("id", datasetInstance.id)
				}
			}
			projections {
				sum("totalSize")
			}
		}
		results[0]
    }
	
	def uniqueUsers(Dataset dataset) {
		def inDataset = dataset ? "where download.dataset.id = '" + dataset.id + "'" : ""
		Download.executeQuery("select count(distinct download.username) from Download download " + inDataset)
	}
	
	def popularDatasets(int max, Dataset dataset) {
		if (dataset) return [dataset]
		Dataset.executeQuery("select dataset from Dataset dataset order by size(dataset.downloads) desc", [max: max])
	}
	
	def popularScenes(int max, Dataset dataset) {
		def inDataset = dataset ? "where scene.sceneFile.dataset.id = '" + dataset.id + "'" : ""
		Scene.executeQuery("select scene from Scene scene " + inDataset + " order by size(scene.downloads) desc", [max: max])
	}
}
