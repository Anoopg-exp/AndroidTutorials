PRODUCT_NAME=hockey
NOTIFY="True"
NOTES="Build uploaded via the upload API"
echo "Downloading File..."
echo "Archives: ${CIRCLE_ARTIFACTS}"
if [ "$1" ]
then
NOTES="$1"
fi
if [ ! ­f "app/build/outputs/apk/app­debug.apk" ]
then
echo "app/build/outputs/apk/app­debug.apk not found!"
else
echo "Uploading to HockeyApp..."

curl \
-F "status=2" \
-F "notify=0" \
-F "ipa=@DBFlowTest\app\build\outputs\apk\app-production-debug.apk" \
-H "X-HockeyAppToken: $HOCKEYAPP_TOKEN" \
https://rink.hockeyapp.net/api/2/apps/cb0a76aee7bd452aa671551e7a028efb/app_versions/upload
fi