# Test project with Selenium with TestNG

## Summary
Selenium과 TestNG를 이용한 테스팅 자동화 프로젝트 **for STUDY!**

## Detail
1. Selenium IDE의 대체제인 Katalon Recorder를 이용하여 테스트 스크립트 생성
2. Export as JAVA(WebDriver + TestNG)
3. Maven Project > 클래스 아무거나 생성 후 > Paste해서 동작여부 테스트

## Environment
* WebDriver 준비
  * IEDriver / ChromeDriver 다운로드
  * 적절한 경로에 locate 시킨 후, 해당 Directory를 환경변수 PATH에 등록
* Eclipse 셋팅
  * Marketplace에서 Testng 다운로드 & 인스톨
* Maven Dependencies 설정
  * Selenium
    ~~~
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>3.9.1</version>
    </dependency>
    ~~~   
  * TestNG
    ~~~
    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>6.14.3</version>
      <scope>test</scope>
    </dependency>
    ~~~
