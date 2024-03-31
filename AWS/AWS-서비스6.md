## AWS의 이벤트 처리
1. SQS와 Lambda를 이용한 처리
- 이벤트가 SQS 대기열에 삽입되고, 람다 서비스가 SQS 대기열을 풀링한다.
2. Fan out 패턴
- 다중 SQS 대기열에 데이터를 전송하는 방식
- SQS 대기열과 애플리케이션 사이에 SNS 주제를 넣는다. SQS 대기열은 SNS 주제의 구독자가 되고, SNS 주제에
메시지를 전송할 때마다 메시지가 모든 SQS 대기열에 전달된다.
3. S3 이벤트 알림
- Amazon S3 버킷이 특정 이벤트에만 반응하도록 설정할 수 있어 객체 생성, 복원 등을 할 때 알림.
- 사용 사례: Amazon S3에 업로드된 이미지의 썸네일 생성.

## AWS의 고성능 컴퓨팅(HPC)
- 많은 리소스를 즉각적으로 생산할 수 있다. 작업 완료되면 사용량만큼만 비용을 지불한다.
- 사용 사례: 유전체학, 기상 예측, 머신러닝 등
- 1. 데이터 관리 방식
- - AWS Direct Conncect, Snowball, SnowMobile, AWS DataSync
- 2. 컴퓨팅과 네트워킹
- - EC2 인스턴스, 스팟 풀릿, EC2 Enhanced Networking, EFA(Elastic Fabric Adapter)
- 3. 스토리지
- - EBS, 인스턴스 스토어, Amazon S3, Amazon EFS, FSx for Lustre
- 4. 자동화
- - AWS Batch, AWS ParallelCluster(HPC를 AWS에 배포)

## SSM 기타 서비스
1. 명령 실행 : 모든 업데이트를 SNS로 보낼 수 있다. SSM 에이전트로 관리를 통해 EC2 인스턴스를 쉽게 관리할 수 있다.
2. 패치 관리자: 인스턴스 관리 과정 패칭을 자동화하는 데에 사용된다. 운영체제 및 보안 업데이트 적용
3. 유지 관리 기간: 인스턴스에서 작업을 수행할 일정을 정의하는 데에 사용된다.

## AWS Trusted Advisor
- AWS 계정에 대한 전체적인 평가를 제공하고, 5가지 범주에 대한 권고를 제공한다.
- 5가지 범주 : `비용 최적화, 성능, 보안, 고장 내성, 서비스 한도`
- Business&Enterprise는 AWS Support API를 이용해서 프로그램적으로 액세스할 수 있다.