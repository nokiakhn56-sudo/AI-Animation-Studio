# AI Animation Studio APK — Version 0.2

## Is version mein kya kaam karta hai?
- Android app ka Kotlin/Compose interface
- Story input
- 5, 10, 20, 30, 60 minute duration selector
- English/Urdu/Hindi language selector
- Scene-plan draft banata hai

## Kya abhi kaam nahi karta?
Yeh abhi real AI video generator nahi hai. Actual video ke liye secure cloud backend aur AI providers connect karne honge:
- Story-to-scene LLM
- Image/video generation API
- Text-to-speech
- FFmpeg video assembly
- Cloud job queue and file storage

API keys Android app ke andar kabhi hardcode na karein. Backend par secure rakhein.

## Sirf mobile se APK build karne ka tareeqa
1. GitHub account banayein / sign in karein.
2. Is ZIP ko extract karne ke bajaye pehle apne phone par ZIP ko download karein.
3. GitHub par new repository banayein.
4. Repository mein project files upload karein. ZIP ko repository mein as a ZIP upload karne se workflow source files nahi dekh payega; pehle ZIP extract karke andar ki files upload karein.
5. Repository ke Actions tab mein "Build Android APK" workflow run karein.
6. Build complete hone par workflow run ke Artifacts section se `AI-Animation-Studio-debug-apk` download karein.
7. APK ko phone par download karke install karein. Android aapse browser/file manager ke liye install permission maang sakta hai.

## Zaroori
- Yeh starter build debug APK banata hai; Play Store release ke liye signed release build alag se banana hota hai.
- GitHub account aur internet chahiye.
- AI video APIs free/low-cost ho sakti hain lekin unki free limits badalti rehti hain; long video generation aam tor par multiple paid jobs leti hai.
- Agar aap minor hain, API accounts ya payments ke liye parent/guardian ki madad lein.

## Build setup note
Gradle wrapper automatically workflow mein banaya jata hai. Android Studio/PC ki zaroorat build ke liye nahi, kyunki GitHub Actions cloud runner par APK compile karta hai.
