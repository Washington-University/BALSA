package balsa

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import balsa.file.FileMetadata
import balsa.file.SceneFile
import balsa.scene.Scene
import groovy.sql.Sql
import org.hibernate.SessionFactory

class StatsService {
	SessionFactory sessionFactory
	
	def getDownloadStats(int max, Dataset dataset) {
		[
			totalDownloads: totalDownloads(dataset), 
			mostRecentDownload: mostRecentDownload(dataset), 
			totalDownloadSize: totalDownloadSize(dataset), 
			uniqueUsers: uniqueUsers(dataset),
			popularDatasets: popularDatasets(max, dataset), 
			popularScenes: popularScenes(max, dataset),
			popularFiles: popularFiles(max, dataset),
			downloadsByMonth: downloadsByMonth(max, dataset)
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
		def sql = new Sql(sessionFactory.currentSession.connection())
		def inDataset = ''
		if (dataset) {
			def fileIds = sql.rows("select id from file_metadata where dataset_id = '" + dataset.id + "'")
			def sceneIds = sql.rows("select id from scene where scene_file_id in (" + formatList(fileIds) + ")")
			inDataset = 'where scene_downloads_id in (' + formatList(sceneIds) + ')'
		}
		sql.rows("select s.id, s.name, s.short_name, s.scene_line_id, s.scene_file_id, f.filename, sd.dc, f.dataset_id, d.class as dclass, d.title, d.short_title from (select scene_downloads_id as id, count(download_id) as dc from scene_download " + inDataset + " group by scene_downloads_id) sd join scene s on s.id = sd.id join file_metadata f on s.scene_file_id = f.id join dataset d on d.id = f.dataset_id order by sd.dc desc limit " + max)
	}

	def popularFiles(int max, Dataset dataset) {
		def sql = new Sql(sessionFactory.currentSession.connection())
		def fileIds = sql.rows("select id from file_metadata" + (dataset ? " where dataset_id = '" + dataset.id + "'" : ""))
		def sceneIds = sql.rows("select id from scene where scene_file_id in (" + formatList(fileIds) + ")")
		def sceneDownloadIds = sql.rows("select download_id from scene_download where scene_downloads_id in (" + formatList(sceneIds) + ")")
		def nonSceneDownloadIds = sql.rows("select id from download where" + (dataset ? " dataset_id = '" + dataset.id + "' and" : "") + " id not in (" + formatList(sceneDownloadIds) + ")")
		sql.rows("select f.id, f.filename, fd.dc, f.dataset_id, d.class as dclass, d.title, d.short_title from (select file_metadata_downloads_id as id, count(download_id) as dc from file_metadata_download where download_id in (" + formatList(nonSceneDownloadIds) + ") group by file_metadata_downloads_id) fd join file_metadata f on f.id = fd.id join dataset d on d.id = f.dataset_id order by fd.dc desc limit " + max)
	}

	def formatList(map) {
		def flatList = []
		for (item in map) {
			flatList.addAll(item.values())
		}
		def formattedList = "'" + flatList.join("','") + "'"
		return formattedList
	}

	def downloadsByMonth(int max, Dataset dataset) {
		if (dataset && !dataset.publicDate) {
			return null
		}
		def inDataset = dataset && dataset.publicDate ? " where dataset_id = '" + dataset.id + "' and date >= '" + dataset.publicDate.format('yyyy-MM-dd') + "'" : ""
		def sql = new Sql(sessionFactory.currentSession.connection())
		sql.rows("select to_char(date_trunc('month', date), 'YY-MM') as month, count(id) as count from download" + inDataset + " group by date_trunc('month', date) order by date_trunc('month', date) asc") as JSON
	}
}