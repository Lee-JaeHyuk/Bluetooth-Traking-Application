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

- GPS API 비교
|구분|장점|단점|
|------|---|---|
|Google|1.해외에서 사용 용이 2.인터넷 연결 없이도 사용 가능|국내 사용보단 해외 사용에 용이|
|Naver|1.직관적인 아이콘 사용 2.나침반 기능과 거리뷰 기능 사용|경로 탐색 시 미묘한 오류 존재|
|KaKao|1.일반 로드뷰 제공 2.스카이 view, 3D view 제공|대중 교통 및 도보 사용시 오류 존재|

- GoogleGpsAPI 선택 이유
BlueTooth Traking System의 경우 가장 중요한 것은 정확한 데이터이다. 어디서 사용하고 어디서 마지막으로 사용했는지에 대한 정보가 데이터의 유무와 상관없이 항상 서비스 가능한 Google GPS를 선택한 이유이다. 또한 Android Studio에서 가장 쉽게 접근 가능한 API가 Google API 인 점 또한 무시할 수 없었다. <br/><br/>


#### 3-2. BroadCast Receiver

- 개념
Broadcast Receiver는 특정 이벤트가 발생할 경우 알림을 주는 컴포넌트이다. 어느 특정 방송이 올때까지 대기하고 있다가 특정방송의 시그널이 나오면 기다렸던 action을 수행하는 일종의 event handler라고 볼 수 있다.

- Bluetooth Broadcast Receiver
현재 안드로이드 스튜디오에서 제공하는 Bluetooth Broadcast Receiver를 활용하여 무선 이어폰 특성상 휴대폰과의 paring 과 unparing event를 기다리는 Broadcast Receiver를 생성했다. <br/><br/>


#### 3-3.Database - SQLite

- Database
여러 사람이 공유하고 사용할 목적으로 통합 관리되는 정보의 집합이다. 논리적으로 연관된 하나 이상의 자료의 모음으로 그 내용을 고도로 구조화 함으로써 검색과 갱신의 효율화를 꾀한 것이다. 즉, 몇 개의 자료 파일을 조직적으로 통합하여 자료 항목의 중복을 없애고 자료를 구조화 하여 기억시켜 놓은 자료의 집합체라고 할 수 있다.

- SQLite
SQLite는 MySQL이나 PostgreSQL과 같은 데이터베이스 관리 시스템이지만, 서버가 아니라 응용프로그램에 넣어 사용하는 비교적 가벼운 데이터베이스 시스템이다. 현재 안드로이드 스튜디오 내에도 SQLite를 넣어 활용 가능하다. <br/><br/>

#### 3-4.GeoCording
- Geocoding
Geocoding은 고유명칭을 가지고 위도와 경도의 좌표 값을 얻는 것을 말한다. 
- Reverse Geocoding
Reverse Geocoding은 Geocoding과는 반대로 현재의 위도와 경도의 좌표를 통해 해당 주소의 고유명칭을 갖고 오는 것을 말한다. <br/><br/>
















