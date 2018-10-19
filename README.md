# Test Automation with Selenium

## Purpose
**For STUDY!**

Selenium을 이용하여 UI 테스트를 자동화 해보고자 시작한 프로젝트.

## 테스트 스크립트 작성 과정
1. Selenium IDE의 대체제인 Katalon Recorder를 이용하여 테스트 스크립트 생성
2. Export as JAVA(WebDriver + TestNG)
3. Maven Project > 클래스 아무거나 생성 후 > Paste해서 동작여부 테스트
4. 제대로 동작하지 않는 경우 Selenium code를 적절히 수정

## Environment
* WebDriver 준비
  * IEDriver / ChromeDriver 다운로드
  * 적절한 경로에 locate 시킨 후, 해당 Directory를 환경변수 PATH에 등록
* Eclipse 셋팅
  * Marketplace에서 TestNG 설치
* Windows DPI 설정
  * Windows 7 기준, 권장 확대 비율이 125%로 되어있어서 정상적인 화면 테스트가 안되고,
    IE의 경우는 아예 Exception이 떨어짐. (해결책은 있으나 이미 deprecate 되어있음)
  * 해결책 : Start menu - dpi 검색 - 100%로 변경
