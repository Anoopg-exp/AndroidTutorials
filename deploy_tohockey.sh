PRODUCT_NAME=hockey
NOTIFY="True"
NOTES="Build uploaded via the upload API"
echo "Downloading File..."
echo "Archives: ${CIRCLE_ARTIFACTS}"
if [ "$1" ]
then
NOTES="$1"
fi

file ="app/build/outputs/apk/app-production-debug.apk"
if [  ­f  $file]
then
echo "Uploading to HockeyApp..."

else
echo "app/build/outputs/apk/app-production-debug.apk not found!"
fi