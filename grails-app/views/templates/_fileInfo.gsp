<g:if test="${file.fileInfo}">
    <g:if test="${file.fileInfo.Structure}">
        <p>
            <span class="attributeLabel">STRUCTURE:</span><br>
            ${file.fileInfo.Structure.value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Maps to Surface'] || file.fileInfo['Maps to Volume'] || file.fileInfo['Maps with LabelTable'] || file.fileInfo['Maps with Palette'] || file.fileInfo['All Map Palettes Equal']}">
        <span class="attributeLabel">FILE PROPERTIES:</span><br>
        <table class="table table-bordered table-sm mb-3 w-50 text-center">
            <thead>
                <tr>
                    <g:if test="${file.fileInfo['Maps to Surface']}">
                        <td>Maps to Surface</td>
                    </g:if>
                    <g:if test="${file.fileInfo['Maps to Volume']}">
                        <td>Maps to Volume</td>
                    </g:if>
                    <g:if test="${file.fileInfo['Maps with LabelTable']}">
                        <td>Maps with LabelTable</td>
                    </g:if>
                    <g:if test="${file.fileInfo['Maps with Palette']}">
                        <td>Maps with Palette</td>
                    </g:if>
                    <g:if test="${file.fileInfo['All Map Palettes Equal']}">
                        <td>All Map Palettes Equal</td>
                    </g:if>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <g:if test="${file.fileInfo['Maps to Surface']}">
                        <g:if test="${file.fileInfo['Maps to Surface'].value == 'true'}"><td class="text-success">true</td></g:if>
                        <g:else><td class="text-danger">false</td></g:else>
                    </g:if>
                    <g:if test="${file.fileInfo['Maps to Volume']}">
                        <g:if test="${file.fileInfo['Maps to Volume'].value == 'true'}"><td class="text-success">true</td></g:if>
                        <g:else><td class="text-danger">false</td></g:else>
                    </g:if>
                    <g:if test="${file.fileInfo['Maps with LabelTable']}">
                        <g:if test="${file.fileInfo['Maps with LabelTable'].value == 'true'}"><td class="text-success">true</td></g:if>
                        <g:else><td class="text-danger">false</td></g:else>
                    </g:if>
                    <g:if test="${file.fileInfo['Maps with Palette']}">
                        <g:if test="${file.fileInfo['Maps with Palette'].value == 'true'}"><td class="text-success">true</td></g:if>
                        <g:else><td class="text-danger">false</td></g:else>
                    </g:if>
                    <g:if test="${file.fileInfo['All Map Palettes Equal']}">
                        <g:if test="${file.fileInfo['All Map Palettes Equal'].value == 'true'}"><td class="text-success">true</td></g:if>
                        <g:else><td class="text-danger">false</td></g:else>
                    </g:if>
                </tr>
            </tbody>
        </table>
    </g:if>

    <g:if test="${file.fileInfo['Orthogonal']}">
        <p>
            <span class="attributeLabel">Orthogonal:</span><br>
            ${file.fileInfo['Orthogonal'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Dimensions']}">
        <p>
            <span class="attributeLabel">Dimensions:</span><br>
            ${file.fileInfo['Dimensions'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['sform']}">
        <span class="attributeLabel">sform:</span><br>
        <table class="table table-bordered table-sm mb-3 w-auto text-right">
            <tbody>
            <g:each in="${file.fileInfo['sform'].value.split(',')}">
                <tr>
                    <g:each in="${it.trim().split(' ')}">
                        <td class="w-25">${it}</td>
                    </g:each>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:if>

    <g:if test="${file.fileInfo['Number of Rows']}">
        <p>
            <span class="attributeLabel">Number of Rows:</span><br>
            ${file.fileInfo['Number of Rows'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Number of Columns']}">
        <p>
            <span class="attributeLabel">Number of Columns:</span><br>
            ${file.fileInfo['Number of Columns'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Number of Vertices']}">
        <p>
            <span class="attributeLabel">Number of Vertices:</span><br>
            ${file.fileInfo['Number of Vertices'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Maps to Volume'] == 'true'}">
    <g:if test="${file.fileInfo['Volume Dim[0]']}">
        <p>
            <span class="attributeLabel">Volume Dim[0]:</span><br>
            ${file.fileInfo['Volume Dim[0]'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Volume Dim[1]']}">
        <p>
            <span class="attributeLabel">Volume Dim[1]:</span><br>
            ${file.fileInfo['Volume Dim[1]'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Volume Dim[2]']}">
        <p>
            <span class="attributeLabel">Volume Dim[2]:</span><br>
            ${file.fileInfo['Volume Dim[2]'].value}
        </p>
    </g:if>
    </g:if>

    <g:if test="${file.fileInfo['Palette Type']}">
        <p>
            <span class="attributeLabel">Palette Type:</span><br>
            ${file.fileInfo['Palette Type'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['ALONG_ROW map type']}">
        <div class="mb-3">
            <span class="attributeLabel">ALONG_ROW Map:</span><br>
            <g:if test="${file.fileInfo['ALONG_ROW map type'].size() > 5}">
                <a class="btn btn-light" data-toggle="collapse" data-target="#along_row_info">click to show/hide</a>
                <div id="along_row_info" class="collapse">
                    <ul class="mb-0">
                        <g:each in="${file.fileInfo['ALONG_ROW map type']}" var="item">
                            <li>
                                <g:if test="${item.key != 'value'}">${item.key}: </g:if>
                                <g:if test="${item.value.value.contains(';')}">
                                    <table class="table table-bordered table-sm w-auto text-right">
                                        <tbody>
                                        <g:each in="${item.value.value.split(';')}">
                                            <tr>
                                                <g:each in="${it.trim().split(',')}">
                                                    <td class="w-25">${it}</td>
                                                </g:each>
                                            </tr>
                                        </g:each>
                                        </tbody>
                                    </table>
                                </g:if>
                                <g:else>
                                    ${item.value.value}
                                </g:else>
                                <g:if test="${item.value instanceof Map && (item.value['CortexLeft'] || item.value['CortexRight'])}">
                                    <ul>
                                        <g:if test="${item.value['CortexLeft']}"><li>CortexLeft: ${item.value['CortexLeft'].value}</li></g:if>
                                        <g:if test="${item.value['CortexRight']}"><li>CortexRight: ${item.value['CortexRight'].value}</li></g:if>
                                    </ul>
                                </g:if>
                            </li>
                        </g:each>
                    </ul>
                </div>
            </g:if>
            <g:else>
                <ul class="mb-0">
                    <g:each in="${file.fileInfo['ALONG_ROW map type']}" var="item">
                        <li>
                            <g:if test="${item.key != 'value'}">${item.key}: </g:if>
                            <g:if test="${item.value.value.contains(';')}">
                                <table class="table table-bordered table-sm w-auto text-right">
                                    <tbody>
                                    <g:each in="${item.value.value.split(';')}">
                                        <tr>
                                            <g:each in="${it.trim().split(',')}">
                                                <td class="w-25">${it}</td>
                                            </g:each>
                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </g:if>
                            <g:else>
                                ${item.value.value}
                            </g:else>
                            <g:if test="${item.value instanceof Map && (item.value['CortexLeft'] || item.value['CortexRight'])}">
                                <ul>
                                    <g:if test="${item.value['CortexLeft']}"><li>CortexLeft: ${item.value['CortexLeft'].value}</li></g:if>
                                    <g:if test="${item.value['CortexRight']}"><li>CortexRight: ${item.value['CortexRight'].value}</li></g:if>
                                </ul>
                            </g:if>
                        </li>
                    </g:each>
                </ul>
            </g:else>
        </div>
    </g:if>

    <g:if test="${file.fileInfo['ALONG_COLUMN map type']}">
        <div class="mb-3">
            <span class="attributeLabel">ALONG_COLUMN Map:</span><br>
            <g:if test="${file.fileInfo['ALONG_COLUMN map type'].size() > 5}">
                <a class="btn btn-light" data-toggle="collapse" data-target="#along_col_info">click to show/hide</a>
                <div id="along_col_info" class="collapse">
                    <ul class="mb-0">
                        <g:each in="${file.fileInfo['ALONG_COLUMN map type']}" var="item">
                            <li>
                                <g:if test="${item.key != 'value'}">${item.key}: </g:if>
                                <g:if test="${item.value.value.contains(';')}">
                                    <table class="table table-bordered table-sm w-auto text-right">
                                        <tbody>
                                        <g:each in="${item.value.value.split(';')}">
                                            <tr>
                                                <g:each in="${it.trim().split(',')}">
                                                    <td class="w-25">${it}</td>
                                                </g:each>
                                            </tr>
                                        </g:each>
                                        </tbody>
                                    </table>
                                </g:if>
                                <g:else>
                                    ${item.value.value}
                                </g:else>
                                <g:if test="${item.value instanceof Map && (item.value['CortexLeft'] || item.value['CortexRight'])}">
                                    <ul>
                                        <g:if test="${item.value['CortexLeft']}"><li>CortexLeft: ${item.value['CortexLeft'].value}</li></g:if>
                                        <g:if test="${item.value['CortexRight']}"><li>CortexRight: ${item.value['CortexRight'].value}</li></g:if>
                                    </ul>
                                </g:if>
                            </li>
                        </g:each>
                    </ul>
                </div>
            </g:if>
            <g:else>
                <ul class="mb-0">
                    <g:each in="${file.fileInfo['ALONG_COLUMN map type']}" var="item">
                        <li>
                            <g:if test="${item.key != 'value'}">${item.key}: </g:if>
                            <g:if test="${item.value.value.contains(';')}">
                                <table class="table table-bordered table-sm w-auto text-right">
                                    <tbody>
                                    <g:each in="${item.value.value.split(';')}">
                                        <tr>
                                            <g:each in="${it.trim().split(',')}">
                                                <td class="w-25">${it}</td>
                                            </g:each>
                                        </tr>
                                    </g:each>
                                    </tbody>
                                </table>
                            </g:if>
                            <g:else>
                                ${item.value.value}
                            </g:else>
                            <g:if test="${item.value instanceof Map && (item.value['CortexLeft'] || item.value['CortexRight'])}">
                                <ul>
                                    <g:if test="${item.value['CortexLeft']}"><li>CortexLeft: ${item.value['CortexLeft'].value}</li></g:if>
                                    <g:if test="${item.value['CortexRight']}"><li>CortexRight: ${item.value['CortexRight'].value}</li></g:if>
                                </ul>
                            </g:if>
                        </li>
                    </g:each>
                </ul>
            </g:else>
        </div>
    </g:if>

    <g:if test="${file.fileInfo['X-minimum'] && file.fileInfo['X-maximum']}">
        <p>
            <span class="attributeLabel">X RANGE:</span><br>
            [${file.fileInfo['X-minimum'].value}, ${file.fileInfo['X-maximum'].value}]
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Y-minimum'] && file.fileInfo['Y-maximum']}">
        <p>
            <span class="attributeLabel">Y RANGE:</span><br>
            [${file.fileInfo['Y-minimum'].value}, ${file.fileInfo['Y-maximum'].value}]
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Z-minimum'] && file.fileInfo['Z-maximum']}">
        <p>
            <span class="attributeLabel">Z RANGE:</span><br>
            [${file.fileInfo['Z-minimum'].value}, ${file.fileInfo['Z-maximum'].value}]
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Orientation[0]']}">
        <p>
            <span class="attributeLabel">Orientation[0]:</span><br>
            ${file.fileInfo['Orientation[0]'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Orientation[1]']}">
        <p>
            <span class="attributeLabel">Orientation[1]:</span><br>
            ${file.fileInfo['Orientation[1]'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Orientation[2]']}">
        <p>
            <span class="attributeLabel">Orientation[2]:</span><br>
            ${file.fileInfo['Orientation[2]'].value}
        </p>
    </g:if>

    <g:if test="${file.fileInfo['Spacing']}">
        <p>
            <span class="attributeLabel">Spacing:</span><br>
            ${file.fileInfo['Spacing'].value}
        </p>
    </g:if>
</g:if>