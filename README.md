데이터 소스 분리 샘플 [![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FjYeory%2Fseparate-db-connection&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)
---
프로젝트를 진행하다 보면 부득이 두개의 데이터베이스에서 데이터를 가져와야 할 때도 있다.
- Writer / Reader 
- 사용자 / 데이터
등의 경우??

1 WAS 1 DB를 지향하는 나로써 위 구조는 그닥 맘에 들지 않지만 어쩔 수 없이 해야 할 경우가 생긴다면... 

각 데이터 소스를 적용할 패키지 구조를 나누는 것이 가장 좋다.

이 프로젝트에서는 아래와 같이 나눠져 있다.
- 사용자 : com.jyeory.www.user -> user_db
- 데이터 : com.jyeory.www.data -> data_db

두 개의 데이터 소스를 만들되 데이터 소스와 연결할 Repository 패키지를 지정하면 깔끔하게 사용할 수 있다.


curl --location --request GET 'localhost:18080/country?countryCd=KOR'

curl --location --request GET 'localhost:18080/member?idx=99'
