curl \
-F "status=2" \
-F "notify=0" \
-F "ipa=@DBFlowTest\app\build\outputs\apk\billingapp_v_1.2(50)-production-debug.apk" \
-H "X-HockeyAppToken: $HOCKEYAPP_TOKEN" \
https://rink.hockeyapp.net/api/2/apps/cb0a76aee7bd452aa671551e7a028efb/app_versions/upload