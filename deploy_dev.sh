if [ ! ­f "app/build/outputs/apk/app­debug.apk" ]
 echo "app/build/outputs/apk/app­debug.apk not found!"
else
 echo "Uploading to HockeyApp..."
curl \
-F "status=2" \
-F "notify=0" \
-F "ipa=@app\build\outputs\apk\app-production-debug.apk" \
-H "X-HockeyAppToken: $HOCKEYAPP_TOKEN" \
https://rink.hockeyapp.net/api/2/apps/cb0a76aee7bd452aa671551e7a028efb/app_versions/upload