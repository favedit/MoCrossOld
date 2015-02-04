SET SOURCE_HOME=%CD%\..\..\..\Build\Debug_x64
COPY /Y "%SOURCE_HOME%\MoCommonD.dll"         "C:\Program Files\Autodesk\3ds Max 2014\MoCommonD.dll"
COPY /Y "%SOURCE_HOME%\Mo3ds2014Exporter.dll" "C:\Program Files\Autodesk\3ds Max 2014\Mo3ds2014Exporter.dll"
COPY /Y "%SOURCE_HOME%\Mo3ds2014Export.dle"   "C:\Program Files\Autodesk\3ds Max 2014\stdplugs\Mo3ds2014Export.dle"
