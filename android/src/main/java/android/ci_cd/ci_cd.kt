package android.ci_cd

/*
CI:
    1- Upload project to the Github
    2- Open project in Github website
    3- Choose "Action"
    4- Search "Android CI"
    5- Choose configure
    6- Choose "Start commit"
    7- Choose "Commit new file"
    8- Pull project in AndroidStudio
    9- CI is already active in your github action website

CD:
    01- Open Firebase website
    02- create project on it
    03- Choose project
    04- Choose android
    05- Register app
    06- choose "App distribution"
    07- Add email in "Tester and group" section
    08- search firebase
    09- choose "App distribution"
    10- Complete part 1 and two easily
    11- part three if doesn't accept enter for command, press control enter at the same time
    12- copy given token to Github website-> your project-> setting -> secret-> action->
        new repository secret
    13- Add key and name
    14- Add firebaseAppDistribution as section 4 sample
    15- Add firebaseToken on Windows environmental_variable and User section by FIREBASE_TOKEN name
    16- Restart pc
    17- gradlew assembleDebug appDistributionUploadRelease
    18- Go to Firebase consul-> App distribution-> release
    19- Add Github website->your project-> action-> new workflow-> set up a workflow your self->
    setup by deploy.yml data
    20- sync project
    21- run ./gradlew assembleDebug; ./gradlew appDistributionUploadDebug
    22- Go to Firebase website-> app distribution-> release-> add tester email address
    23- go to tester device and flow install test app
 */