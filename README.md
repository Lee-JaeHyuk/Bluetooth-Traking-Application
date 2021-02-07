# Bluetooth-Traking-Application
## Google GPS API 를 활용한 블루투스 이어폰 찾기 애플리케이션<br/><br/>


### **1. 프로젝트 목표**

Bluetooth-Traking-Application은 휴대폰 내 GPS를 이용하여 본인의 블루투스 사용 제품의 마지막 위치와 시간을 제공함으로 분실 시 되찾는데 도움을 주는 서비스 프로그램이다. <br/><br/>


### **2. 프로젝트 개발 환경**

S/W 개발 환경
* - OS : Window 10
* - IDE : Android Studio
* - Language : Java

H/W 개발 환경
* - Device : Samsung Smart Phone<br/><br/>

### **3. 프로젝트 적용 기술**

#### 3-1. Google GPS API

- 블루투스 이어폰에는 내장 GPS가 없기 때문에 블루투스 이어폰 특성상 휴대폰과 연동되어야 한다는 점을 이용하여 휴대폰 내 자체 GPS를 활용하여 지도의 사용자 위치를 표시하는 형식으로 사용되어야 한다.

- 비교
|구분|장점|단점|
|Google|1.해외에서 사용 용이
2.인터넷 연결 없이도 사용 가능|국내 사용보단 해외 사용에 용이|
|Naver|1.직관적인 아이콘 사용
2.나침반 기능과 거리뷰 기능 사용|경로 탐색 시 미묘한 오류 존재|
|KaKao|1.일반 로드뷰 제공
2.스카이 view, 3D view 제공|대중 교통 및 도보 사용시 오류 존재|

- BlueTooth Traking System의 경우 가장 중요한 것은 정확한 데이터이다. 어디서 사용하고 어디서 마지막으로 사용했는지에 대한 정보가 데이터의 유무와 상관없이 항상 서비스 가능한 Google GPS를 선택한 이유이다. 또한 Android Studio에서 가장 쉽게 접근 가능한 API가 Google API 인 점 또한 무시할 수 없었다.


#### 3-2. BroadCast Receiver

- OpenCV
이미지의 특정 위치(주차구역)를 자른 이미지 파일 저장
- Yolov3
자른 이미지 파일이 자동차인지 판단하여 자동차 유무 결정
자동차 유무에 대한 리스트 저장<br/><br/>

#### 3-3.Database - SQLite
- Pygame
주차구역에 대한 정보(자동차 유무)가 표시된 주차 도면을 스크린으로 출력
- Pandas
주차장 정보 테이블을 데이터 프레임으로 출력<br/><br/>

#### 3-4.GeoCording
- QR code 생성을 도와주는 파이썬 모듈<br/><br/>
















