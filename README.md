# batch-government-general
전국 일반 음식점 표준 데이터 정기 배치 프로젝트

## What you’ll need
- About 20 minutes
- A favorite text editor or IDE
- JDK 1.17+
- Gradle 8+
- SpringBoot 3.4.0
    - Batch
    - Jpa
- Mysql 9.0.1
- Lombok 1.18.36

## Settings
- Mysql-Pre-Installed
> - brew install mysql   
> - mysql -u root   
> - mysql server start   

> - mysql_secure_installation
> - Would you like to setup VALIDATE PASSWORD component? No   
> - Remove anonymous users? Yes   
> - Disallow root login remotely? Yes   
> - Remove test database and access to it? Yes   
> - Reload privilege tables now? Yes   

> - mysql -u root -p   
> - exit   
> - mysql.server stop
- Schema-Pre-Installed
```sql
-- 기본 데이터베이스 생성
    create schema danal_restaurant_info collate utf8mb4_general_ci;

-- 스프링 배치 기본 테이블
    create table if not exists danal_restaurant_info.BATCH_JOB_EXECUTION_SEQ (
        ID         bigint not null,
        UNIQUE_KEY char   not null,
        constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
    );

    create table if not exists danal_restaurant_info.BATCH_JOB_INSTANCE (
        JOB_INSTANCE_ID bigint       not null primary key,
        VERSION         bigint       null,
        JOB_NAME        varchar(100) not null,
        JOB_KEY         varchar(32)  not null,
        constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
    );

    create table if not exists danal_restaurant_info.BATCH_JOB_EXECUTION (
        JOB_EXECUTION_ID bigint        not null primary key,
        VERSION          bigint        null,
        JOB_INSTANCE_ID  bigint        not null,
        CREATE_TIME      datetime(6)   not null,
        START_TIME       datetime(6)   null,
        END_TIME         datetime(6)   null,
        STATUS           varchar(10)   null,
        EXIT_CODE        varchar(2500) null,
        EXIT_MESSAGE     varchar(2500) null,
        LAST_UPDATED     datetime(6)   null,
        constraint JOB_INST_EXEC_FK
        foreign key (JOB_INSTANCE_ID) references danal_restaurant_info.BATCH_JOB_INSTANCE (JOB_INSTANCE_ID)
    );

    create table if not exists danal_restaurant_info.BATCH_JOB_EXECUTION_CONTEXT (
        JOB_EXECUTION_ID   bigint        not null primary key,
        SHORT_CONTEXT      varchar(2500) not null,
        SERIALIZED_CONTEXT text          null,
        constraint JOB_EXEC_CTX_FK
        foreign key (JOB_EXECUTION_ID) references danal_restaurant_info.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
    );

    create table if not exists danal_restaurant_info.BATCH_JOB_EXECUTION_PARAMS (
        JOB_EXECUTION_ID bigint        not null,
        PARAMETER_NAME   varchar(100)  not null,
        PARAMETER_TYPE   varchar(100)  not null,
        PARAMETER_VALUE  varchar(2500) null,
        IDENTIFYING      char          not null,
        constraint JOB_EXEC_PARAMS_FK
        foreign key (JOB_EXECUTION_ID) references danal_restaurant_info.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
    );

    create table if not exists danal_restaurant_info.BATCH_JOB_SEQ (
        ID         bigint not null,
        UNIQUE_KEY char   not null,
        constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
    );

    create table if not exists danal_restaurant_info.BATCH_STEP_EXECUTION (
        STEP_EXECUTION_ID  bigint        not null primary key,
        VERSION            bigint        not null,
        STEP_NAME          varchar(100)  not null,
        JOB_EXECUTION_ID   bigint        not null,
        CREATE_TIME        datetime(6)   not null,
        START_TIME         datetime(6)   null,
        END_TIME           datetime(6)   null,
        STATUS             varchar(10)   null,
        COMMIT_COUNT       bigint        null,
        READ_COUNT         bigint        null,
        FILTER_COUNT       bigint        null,
        WRITE_COUNT        bigint        null,
        READ_SKIP_COUNT    bigint        null,
        WRITE_SKIP_COUNT   bigint        null,
        PROCESS_SKIP_COUNT bigint        null,
        ROLLBACK_COUNT     bigint        null,
        EXIT_CODE          varchar(2500) null,
        EXIT_MESSAGE       varchar(2500) null,
        LAST_UPDATED       datetime(6)   null,
        constraint JOB_EXEC_STEP_FK
        foreign key (JOB_EXECUTION_ID) references danal_restaurant_info.BATCH_JOB_EXECUTION (JOB_EXECUTION_ID)
    );

    create table if not exists danal_restaurant_info.BATCH_STEP_EXECUTION_CONTEXT (
        STEP_EXECUTION_ID  bigint        not null primary key,
        SHORT_CONTEXT      varchar(2500) not null,
        SERIALIZED_CONTEXT text          null,
        constraint STEP_EXEC_CTX_FK
        foreign key (STEP_EXECUTION_ID) references danal_restaurant_info.BATCH_STEP_EXECUTION (STEP_EXECUTION_ID)
    );

    create table if not exists danal_restaurant_info.BATCH_STEP_EXECUTION_SEQ (
        ID         bigint not null,
        UNIQUE_KEY char   not null,
        constraint UNIQUE_KEY_UN unique (UNIQUE_KEY)
    );

-- 음식정 정보 쓰기 테이블
    create table if not exists danal_restaurant_info.government_general_restaurant (
        id                                      bigint auto_increment comment '기본키' primary key,
        number                                  bigint       null comment '일련 번호',
        open_service_name                       varchar(255) null comment '개방서비스명',
        open_service_id                         varchar(255) null comment '개방서비스아이디',
        local_government_code                   varchar(255) null comment '개방자치단체코드',
        management_number                       varchar(255) null comment '관리번호',
        permit_date                             varchar(255) null comment '인허가일자',
        permit_cancellation_date                varchar(255) null comment '인허가취소일자',
        business_status_code                    varchar(255) null comment '영업상태구분코드',
        business_status_name                    varchar(255) null comment '영업상태명',
        detailed_business_status_code           varchar(255) null comment '상세영업상태코드',
        detailed_business_status_name           varchar(255) null comment '상세영업상태명',
        closure_date                            varchar(255) null comment '폐업일자',
        suspension_start_date                   varchar(255) null comment '휴업시작일자',
        suspension_end_date                     varchar(255) null comment '휴업종료일자',
        reopening_date                          varchar(255) null comment '재개업일자',
        location_phone                          varchar(255) null comment '소재지전화',
        location_area                           varchar(255) null comment '소재지면적',
        location_postal_code                    varchar(255) null comment '소재지우편번호',
        location_full_address                   varchar(255) null comment '소재지전체주소',
        road_name_full_address                  varchar(255) null comment '도로명전체주소',
        road_name_postal_code                   varchar(255) null comment '도로명우편번호',
        business_name                           varchar(255) null comment '사업장명',
        last_modified_time                      varchar(255) null comment '최종수정시점',
        data_update_type                        char         null comment '데이터갱신구분',
        data_update_date                        varchar(255) null comment '데이터갱신일자',
        business_type_name                      varchar(255) null comment '업태구분명',
        coordinate_x                            varchar(255) null comment '좌표정보(x)',
        coordinate_y                            varchar(255) null comment '좌표정보(y)',
        hygiene_type_name                       varchar(255) null comment '위생업태명',
        male_employee_count                     int          null comment '남성종사자수',
        female_employee_count                   int          null comment '여성종사자수',
        business_surrounding_type_name          varchar(255) null comment '영업장주변구분명',
        grade_type_name                         varchar(255) null comment '등급구분명',
        water_supply_facility_type_name         varchar(255) null comment '급수시설구분명',
        total_employee_count                    int          null comment '총직원수',
        headquarters_employee_count             int          null comment '본사직원수',
        factory_office_staff_count              int          null comment '공장사무직직원수',
        factory_sales_staff_count               int          null comment '공장판매직직원수',
        factory_production_staff_count          int          null comment '공장생산직직원수',
        building_ownership_type_name            varchar(255) null comment '건물소유구분명',
        guarantee_amount                        bigint       null comment '보증액',
        monthly_rent_amount                     bigint       null comment '월세액',
        multi_use_facility_yn                   char         null comment '다중이용업소여부',
        total_facility_size                     decimal      null comment '시설총규모',
        traditional_business_designation_number varchar(255) null comment '전통업소지정번호',
        traditional_business_main_food          varchar(255) null comment '전통업소주된음식',
        website                                 varchar(255) null comment '홈페이지'
    ) comment '행정안전부 일반음식점 데이터(20240302)';
```
## Project Structure
- main.java
    - net.danal.batch
        - job (배치 메인 실행 처리기)
            - JobStepRestaurantConfiguration
        - listener (각 기능 실행 단위 로깅용 리스너)
            - ChunkRestaurantListener 
            - JobRestaurantExecutionListener
            - RestaurantItemReadListener
            - RestaurantItemSkipListener
            - RestaurantItemWriteListener
            - StepRestaurantExecutionListener
        - processor (데이터 정합성 필터 용도 중간 처리기)
            - JobRestaurantFlatFileItemProcessor
        - reader (배치 대상 데이터 읽기)
            - JobRestaurantFlatFileItemReader
        - restaurant (음식점 도메인)
            - domain
                - Restaurant (JPA Entity)
                - RestaurantDto (Jdbc POJO DTO)
            - repository (용도별 데이터 접근 레포지토리)
                - RestaurantJdbcRepository
                - RestaurantJpaRepository
        - writer (배치 대상 데이터 읽은 후 쓰기)
            - JobRestaurantFlatFileItemWriter
- resources
    - static (배치 대상 원본 데이터)
        - government_general_restaurant_100_ko.csv
    - application.yml

- test.java
    - net.danal.batch
        - job (최종 Job 통합 테스트용)
            - RestaurantMultiThreadBatchJobTest

## 사전예외조건
(현재 기본요구조건에 대한 부분만 구현었으며, 추후 추가 요구 조건이 충족이 되면 아래 사전 조건 내용은 필요 시 도입 가능)   
- 현재 요구조건에 맞춰 배치기능에만 집중한 상태므로 단일 테이블로 진행한다. 해당 스키마 조회 시 연관 테이블과의 매핑 관계가 필요성이 있다면 추후 정규화를 진행햔다.
- 스키마 정의 시 해당 컬럼이 문자열이며 해당 정보의 길이나 형식이 불확실한 경우 varchar(255) 로 진행한다.(날짜 포함)
- 모든시간은 클라이언트의 현장 시간의 기록이 필요없고 주어진 시간 그대로를 저장하기 때문에 UTC 타입이 아닌 "DATETIME" 으로 진행한다. (날짜타입으로 할 경우)
- 데이터 전반적으로 많은 컬럼에서 빈 값이 들어온다. 필수겂에 대한 정의가 없으므로 모든 컬럼은 null을 허용한다.
- 주어진 일련번호는 중복이 생길 우려와 순서가 보장 되지 않으므로 별도로 참조 할 수 있는 ID 로만 활용한다. 기본키는 자동 생성 ID 를 활용한다.
- 청크 단위 스레드에서 예외 발생 시 해당 트랜잭션 내 롤백이 필요하므로 InnoDB로 선택한다.
- 조회 관련 요구조건은 없으므로 인덱싱 작업은 제외한다.
- 데이터 제공 사이트에서 해당 파일(.csv)을 서버에 다운받거나 API로 데이터 응답을 받아 처리 하지않는다. 그래서 실시간 스케줄링은 처리는 없다.
- 외부 벤더사 및 내부 통신망에서 제공하는 API 연동 조건이 없다. 통신 에러가 없으므로 DB통신 외 연동 관련 예외 처리는 하지않는다.
- 테스트는 API End Point를 만들거나 여러 툴을 사용한 스케줄링을 사용 할 수는 있지만 해당 건은 유닛 테스트 케이스로 수동 처리한다.
- 외, 내부에서 해당 배치 작업을 제어 할 매개변수가 필요 없으므로 JobParameters는 사용 하지 않는다.
- 상시 테스트를 위한 가벼운 버전(100줄) 테스트용배치파일(.csv) 를 준비한다.

## 성능개선 3가지
- JPA "SaveAll" to "jdbcTemplate.batchUpdate" (Bulk Insert)
- ThreadPoolTaskExecutor (Task MultiThread 10ea)
- Batch Chunk (1000)

## 테스트 방법
> - test > java > net > danal > batch > job > RestaurantMultiThreadBatchJobTest.java   

> - 위 테스트 클래스에서 jdbcTemplate_multi_thread_test 실행   
> - 실행 후 저장 데이터 확인 하려면 "after" 삭제 로직 주석처리
> - 가벼운 버전 임시 파일 제공
>   - resources > static > government_general_restaurant_100_ko.csv   
> - 대용량 파일로 테스트를 원한다면 government_general_restaurant_100_ko 이름으로 리소스 폴더에 넣어서 실행한다.

> - 현재 로깅은 Slf4j 를 이용하여 콘솔로 표현했으며, 리스너에서 각 실행 단위 별 Status 및 Exception 를 판단 할 수 있다.

## 아쉬운 점
> - 벌크로 스레드 10개를 이용하여 순서를 보장하지않고 무작위로 삽입한다. 추후 순서가 필요한 배치라면 동시성 문제도 고려해야함.
> - 도메인 확장을 고려하여 "Restaurant" 패키지 처럼 도메인을 분리 했지만 Job 구조에 대해서는 고민이 필요.
> - 정기 스케줄링 연동 (CI-Tool, Quartz 등)과 JobParameter 고려.
> - 무결성에 대해서는 기준 키로 보이는 "Number" 컬럼 기준 한가지만 Null 상황에 Skip 처리 후 로깅만 남긴상태로 나머지 필드값에 대한 고민.
> - 전체 갯수가 정상적으로 들어왔는지 전체 카운트 대비 누락 데이터에 대한 차이를 보여줄수있는 로직처리.

© 2024.12.01 SimJaeHyun