all: img dmg

icns:
	iconutil -c icns rtmlogo.iconset

img:
	tiffutil -cathidpicheck backImage/openrtp_800x400.tif backImage/openrtp_1600x800.tif -out backImage.tiff

dmg:
	rm -f openrtp_v1.2.2.dmg
	create-dmg \
		--volname "OpenRTP Installer" \
		--volicon "icons/openrtp.icns" \
		--background "backImage.tiff" \
		--window-pos 200 120 \
		--window-size 800 440 \
		--icon-size 100 \
		--icon "openrtp.app" 200 190 \
		--hide-extension "OpenRTP.app" \
		--app-drop-link 600 185 \
		"openrtp_v1.2.2.dmg" \
		"bin/openrtp.app/"

clean:
	rm -f .DS_store
	rm -f backImage.tiff
	rm -f *.dmg
