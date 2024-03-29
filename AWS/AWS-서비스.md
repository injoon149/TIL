## IAM 모범 사례

1. 루트 계정은 AWS 계정 설정 때에만 사용한다.
2. AWS 서비스에 권한을 부여할 때마다 IAM 역할을 사용한다.
3. CLI, SDK를 사용할 때 액세스 키를 생성한다.
4. IAM 보안 도구를 사용한다.

## EC2 인스턴스 구매 옵션
1. 온디맨드 인스턴스 : 사용한 사용량만큼 비용이 청구된다. 단기적이고 중단 없는 워크로드가 필요할 때 사용
2. 예약 인스턴스 : 1년 또는 3년의 인스턴스를 예약할 수 있다.
3. Savings Plans : 달러 단위로 특정한 사용량을 약정할 수 있다.
4. 스팟 인스턴스 : 온 디맨드에 비해 최대 90% 할인, 중요한 작업에는 적합x
5. 전용 호스트 : 물리적 서버 전체를 예약하여 인스턴스 배치를 제어한다.(가격은 비싸다)
6. 스팟 플릿 : 스팟 인스턴스를 할당하는 전략을 정의하여, 스팟 인스턴스 세트를 정의한다.

## EC2 배치 그룹
EC2 인스턴스가 AWS 인프라에 배치되는 방식을 제어할 때 쓴다.
1. Cluster : 단일 가용 영역 내에서 지연 시간이 짧은 하드웨어 설정으로 인스턴스를 그룹화할 클러스터 배치 그룹
-> 모든 EC2가 동일한 가용 영역에 있어 Rack이 실패하면 모든 인스턴스가 실패.
2. Spread : 모든 EC2 인스턴스가 다른 하드웨어에 분산되어 위치한다. -> 동시 실패의 위험 감소
3. Partition : 가용 영역의 파티션에 인스턴스 저장. AZ마다 최대 7개의 파티션이 있고, 파티션은 동일한 리전의 여러 가용 영역에 있을 수 있다.

## ENI 

- 탄력적 네트워크 인터페이스
- VPC의 논리적 구성 요소이며 가상 네트워크 카드를 나타낸다.
- EC2 인스턴스가 네트워크에 액세스할 수 있게 한다.
- 주요 사설 IPV4와 하나 이상의 보조 IPV4를 가진다.

## EBS

- 인스턴스가 실행 중인 동안 연결 가능한 네트워크 드라이브, 인스턴스가 종료된 후에도 데이터가 지속된다.
- 다른 AZ 상의 인스턴스에 원래는 연결할 수 없지만 스냅샷을 이용하면 연결 가능하다.
- 종료 시 삭제 기능: 기본적으로 루트 볼륨은 인스턴스 종료와 함께 삭제되도록 설정된다.
다른 EBS 볼륨은 삭제X

## AMI

- EC2 인스턴스를 통해 만든 이미지
- 원하는 소프트웨어 및 운영 체제를 설치할 수 있고 모니터링 툴 추가 가능하다. AMI를 따로 구성하면 부팅 및 설정 시간이 줄어든다.
- EC2 인스턴스에 설치하고자 하는 소프트웨어를 AMI가 패키징해 준다.

## EC2 인스턴스 스토어

- 해당하는 물리적 서버에 연결된 하드웨어 드라이브로, I/O 성능 향상
- 버퍼, 캐시 등 저장하기 위한 좋은 장소 : 단기 스토리지에 적합
- 데이터 손실에 대한 위험성이 존재한다.

## EBS 볼륨 유형
1. gp2/gp3 : 범용 SSD 볼륨으로 다양한 워크로드에 대해 절충안
2. io1/io2 : 최고 성능의 SSD 볼륨으로, 지연시간이 낮고 대용량의 워크로드에 사용한다.
3. st1 : 저비용의 HDD 볼륨, 잦은 접근과 처리량 많은 워크로드에 사용
4. sc1 : 저비용의 HDD 볼륨, 접근 빈도 낮은 워크로드에 사용 
- st1과 sc1은 부팅 볼륨이 될 수 없다.

## Amazon EFS

- 관리형 네트워크 파일 시스템. 다수의 EC2 인스턴스에 마운트될 수 있고 서로 다른 AZ에 
위치해 있을 수 있다. -> 가용성, 확장성이 높다. 온 디맨드로 작용.

## ELB
1. ALB -> HTTP 전용 로드 밸런서로, 머신 간 다수의 HTTP 애플리케이션의 라우팅에 사용된다.
동일 EC2 인스턴스상의 여러 애플리케이션에 부하를 분산한다.
2. NLB -> 가용 영역별로 하나의 고정 IP를 갖고, TCP 및 UDP 프로토콜에서 사용한다.
3. GWLB -> 배포 및 확장과 AWS의 타사 네트워크에 사용. 방화벽 및 보안에 사용한다.

## ELB의 고정 세션
- 로드 밸런서에 2가지 요청을 수행하는 클라이언트가 요청에 응답하기 위해 백엔드에 동일한 인스턴스를 갖는 것.
-> 백엔드 EC2 인스턴스 부하에 불균형을 초래할 수 있다.
- 2가지 유형의 쿠키가 있다. 첫 번째는 애플리케이션 기반 쿠키로, 사용자 지정 쿠키로 만료 기간을 직접 설정 가능하다.
두 번째는 기간 기반 쿠키로, 로드 밸런서에서 생성된 쿠키이다. 이는 특정 기간을 기반으로 만료되며 그 기간이 로드 밸런서 자체에서 생성된다.

## ELB - SSL/TLS 인증서
1. SSL 인증서 : 클라이언트와 로드 밸런서 사이에서 트래픽이 이동하는 동안 암호화해준다. 이를 전송 중 암호화라고 하고, TLS와 동일하게 쓰인다.
2. HTTPS : HTTP + SSL

## SNI
- 여러 개의 SSL 인증서를 하나의 웹 서버에 로드해 하나의 웹 서버가 여러 개의 웹 사이트를 지원할 수 있게 해 준다.
- ALB, NLB, CloudFront에서만 동작한다.

## ASG(오토 스케일링 그룹)
- 증가한 로드, 또는 감소한 로드에 맞춰서 EC2 인스턴스를 추가/제거한다.
- 최소 용량, 희망 용량, 최대 용량을 설정 가능하다.
- 스케일링 정책에는 총 4개의 정책이 있다.
1. 대상 추적 스케일링 : EX) 모든 EC2 인스턴스에서 오토 스케일링 그룹의 평균 CPU
사용률을 추적하여 이 수치가 40%대에 머무르도록 한다.
2. Simple/Step 스케일링: EX) 전체 ASG에 대한 CPU 사용률이 70%를 초과하는 경우 용량 두개 추가
3. 예약 Action : 나와 있는 사용 패턴을 바탕으로 스케일링 예상
4. Predictive 스케일링 : AWS 내 오토 스케일링 서비스를 활용하여 지속적으로 예측을 생성한다.

## RDS - 읽기 전용 복제본과 다중 AZ의 차이
- 읽기 전용 복제본은 단순히 DB에서 읽기할 때, 복제본에서도 읽기할 수 있는 것이다.
- 다중 AZ는 동기식으로 AZ B에 데이터베이스의 모든 내용이 복사된다. 하나의 DNS 이름을 가지며,
마스터에 장애 발생하면 스탠바이 DB가 마스터가 된다.
- 스탠바이 DB는 읽기 및 쓰기 불가하다.(예비 DB 느낌

## Amazon Aurora
- PostgreSQL과 MySQL에 작동. 스토리지가 자동으로 확장되고 즉각적인 장애 조치 가능하다.
- 높은 가용성과 읽기 스케일링이 특징이다.
- 자동 장애 조치, 백업 및 복구, 자동 패치 설치 등 많은 기능을 가지고 있다.
- 고급 개념
1. Custom Endpoints : 다양한 종류의 워크로드에 대해 사용자 정의 엔드포인트를 설정해서
Aurora 복제본의 하위 집합만 쿼리한다.
2. Aurora Serverless : 실제 사용향에 따라 자동화된 데이터베이스 인스턴스화 및 오토 스케일링 제공.
워크로드가 예측할 수 없는 경우에 사용한다.
3. Aurora Multi-Master : Writer 노드에 대해 지속적인 쓰기 가능성을 원하는 경우.
4. Gloval Aurora : 재해 복구에 유용하고 데이터를 리전 간에 복제하는 데 평균 1초 미만 소요.

## Amazon RDS 프록시
- 애플리케이션이 데이터베이스 내에서 데이터베이스 연결 풀을 형성하고 공유할 수 있다.
- 애플리케이션을 RDS 데이터베이스 인스턴스에 일일이 연결하는 대신 프록시에 연결한다.
-> 데이터베이스 효율성 증가, 데이터베이스 연결 시간 최소화.
- 프록시의 장점 : 장애 조치 시간 감소, DB에 IAM 인증을 강제함으로써 IAM 인증을 통해서만
RDS 데이터베이스 인스턴스에 연결하도록 할 수 있다. 자격 증명은 AWS Secrets Manager 서비스에 저장됨.

## Amazon Elasticache
- 캐싱 기술인 Redis 또는 Memcached 관리. RDS에 대한 캐싱을 담당한다.
- Redis는 다중 AZ, 읽기 복제본이 있다. 백업 및 복원 가능.
- Memcached는 데이터 분할 위해 멀티 노드를 사용하여, 가용성x 복제x 영구캐시x 멀티스레드 아키텍처.

## Route 53의 라우팅 정책
- 라우팅 정책은 Route 53이 DNS 쿼리에 응답하는 것을 돕는다.
1. Simple 라우팅 정책 : 트래픽을 단일 리소스로 보내는 방식
2. 가중치 기반 라우팅 정책 : 가중치를 활용해 요청의 일부 비율을 특정 리소스로 보내는 등의
제어가 가능하다. 서로 다른 지역에 걸쳐 로드 밸런싱을 할 때 등에 사용
3. 지연 시간 기반 라우팅 정책 : 가장 가까운 리소스로 리다이렉팅을 하는 정책
4. Geolocation 기반 : 지연 시간과 달리 사용자의 실제 위치를 기반으로 한다. 
5. 지리 근접 라우팅 : 사용자와 리소스의 지리적 위치 기반. 편향값을 사용해 특정 위치를 기반으로 리소스를 더 많은 트래픽으로 이동.
6. IP-Based 기반 : CIDR에 따라 트래픽을 어느 로케이션으로 보내야 할지 정한다.
7. MultiValue 기반 : 트래픽을 다른 리소스로 라우팅할 때 사용한다.

## Route 53의 상태 확인
- 공용 리소스에 대한 상태를 확인하는 방법
- DNS의 장애 조치를 자동화하기 위한 방법
- 총 3가지 방법이 있다.
1. 공용 엔드포인트 모니터링
- 전 세계에서 온 15개의 상태 확인이 엔드 포인트의 상태를 확인하고 임계값을 정상 혹은 비정상으로 설정한다.
- 상태 확인의 작동이 가능하려면 상태 확인이 여러분의 애플리케이션 밸런서나 엔드 포인트에 접근이 가능해야 한다.

2. 계산된 상태 확인(Calculated Health Check)
- 여러 개의 상태 확인 결과를, 하위를 통해 상위 상태 확인 결과를 판단함으로써 하나로 합쳐주는 기능
- 상태 확인이 실패하는 일 없이 상위 상태 확인이 웹사이트를 관리, 유지하도록 하는 경우

3. 개인 리소스의 상태 확인
- CloudWatch 지표를 만들어 CloudWatch 알람을 할당하는 방식이다.
- CloudWatch 메트릭을 이용해 개인 서브넷 안에 있는 EC2 인스턴스를 모니터한다.




