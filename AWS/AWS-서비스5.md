## Site to Site VPN
- VPC는 구축했으나 특정 구조가 있는 기업 데이터 센터를 AWS와 비공개로 연결하려면 기업은 고객 게이트웨이를, VPC는
VPN 게이트웨이를 갖추어야 한다.
- 가상 프라이빗 게이트웨이(VGW) : VPN을 연결하여 AWS 측에 있는 VPN Connector가 생성되면 VPC에 연결
- 고객 게이트웨이 : VPN 연결에서 데이터 센터 담당.
- 서브넷의 VPC에서 라우트 전파를 활성화해야 Site to Site VPN 연결이 실제로 작동한다.
- 온프레미스에서 AWS로 EC2 인스턴스 상태를 진단할 때 보안 그룹 인바운드 ICMP 프로토콜이 활성화되어 있는지 확인한다.

## AWS VPN CloudHub
- 여러 VPN 연결을 통해 모든 사이트 간 안전한 소통을 보장한다.
- VPN만을 사용해 서로 다른 지역 사이 기본 및 보조 네트워크 연결성에 사용한다 => 모든 트래픽이 공용 인터넷을 통해 연결된다.

## Direct Connect(DX)
- 원격 네트워크로부터 VPC로의 전용 프라이빗 연결을 제공한다.
- 전용 연결을 사용하고 AWS Direct Connect 로케이션을 사용한다.
- VPC에는 가상 프라이빗 게이트웨이가 있어야 온프레미스 데이터 센터와 AWS 연결이 가능하다.
- 사용 사례: 대역폭 처리량이 증가할 때, 큰 데이터 세트를 처리할 때

## Direct Connect Gateway
- 다른 리전에 있는 하나 이상의 VPC와 연결하고 싶다면 이를 사용한다.
- 설치 기간이 한달이므로 빠르게 데이터를 전송하고 싶을 때는 사용x. 
- Direct connect + VPN -> IPSEC로 암호화한다.

## Direct Connect 복원력
- 핵심 워크로드의 복원력을 높이기 위해서는 여러 Direct Connnect를 설치한다.
- 이를 최대로 끌어올리려면 각 Direct Connect의 로케이션에 독립적인 연결을 두 개씩 수립하면 복원력이 최대이다.
- Site to Site VPN을 백업으로 두어, 기존 연결에 문제가 발생했을 때 사용할 수도 있다.

## 환승 Gateway(Transit Gateway)
- VPC를 모두 피어링할 필요가 없으며, Transit Gateway를 통해 전이적으로 연결된다.
- Transit Gateway에 라우팅 테이블을 생성해서 어느 VPC가 누구와 통신할지, 어떤 연결이 액세스할지 제한한다.

## VPC 트래픽 미러링
- VPC에서 네트워크 트래픽을 수집하고 검사하되, 방해되지 않는 방식으로 실행하는 것!
- NLB로 트래픽을 보내서 트래픽을 분석한다.
- 사용 사례: 콘텐츠 감사, 네트워킹 문제 해결

## Egress-Only Internet Gateway
- IPV6 트래픽에만 사용되며, NAT Gateway와 비슷하지만 IPV6 전용이다.
- VPC 인스턴스에서 IPV6 아웃바운드 연결을 허용하고, 동시에 인터넷이 인스턴스로 IPV6 연결을 시작하지 못하게 막는다.

## AWS Network Firewall
- 전체 vpc를 방화벽으로 보호하는 서비스, 모든 방향에서 들어오는 모든 트래픽을 검사한다.
- 네트워크 트래픽을 세부적으로 관리할 수 있다. 침입 방지.

## Amazon Pinpoint
- 확장 가능한 양방향 인바운드 및 아웃바운드 마케팅 커뮤니케이션 서비스.
- 고객에게 sms 보내고 답장을 받을 수 있다.
- 사용 사례: 마케팅 이메일을 대량으로 보내거나, 트랜잭션 sms 이미지를 전송하여 캠페인 실행.

## Amazon AppFlow
- SAAS 애플리케이션 및 AWS 사이에 데이터를 전송할 수 있는 완전 관리형 통합 서비스. 소스는 Salesforce 등을 사용한다.
- 데이터를 다양한 곳(s3, redshift)으로 보낼 수 있다.

## AWS Amplify
- 웹 및 모바일 애플리케이션 개발 도구
- AWS의 많은 스택을 한번에 통합해서 애플리케이션 생성 가능.

## SSM Session Manager
- EC2 인스턴스와 온프레미스 서버에서 보안 셸을 시작할 수 있다.

## CloudWatch Log
- AWS에서 애플리케이션 로그를 저장할 수 있다.
- CloudWatch Logs Insights : Cloudwatch Log 안의 쿼리 기능
- CloudWatch Logs Subscription(로그 구독) : 로그 이벤트들의 실시간 스트림을 처리, 분석한다. 이를 kinesis data stream, kinesis data firehose,
람다 등에 전송한다.

