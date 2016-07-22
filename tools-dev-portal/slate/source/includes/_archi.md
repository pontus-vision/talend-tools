# Services Architecture
## Global services and dependencies
<aside class="notice">This are not definitive architecture ! </aside>

<div class="mermaid">

graph LR

    subgraph Gateway
        gateway(Gateway)
    end

    subgraph Users
        tfd-user
        tdp-user
        tds-user
    end
        tds-user -.-> gateway
        tdp-user -.-> gateway
        tfd-user -.-> gateway

    subgraph Common Backend
        kafka
        zookeeper
        mongodb
        schema-registry
        syncope
        postgresql
    end

    %%Backend dependencies
        schema-registry \-\-> kafka
        schema-registry \-\-> zookeeper
        %% decanter -.-> kafka
        kafka \-\-> zookeeper
        syncope -.-> postgresql


    subgraph Shared Services
        tss-repository
        tss-schema-mgmt
        tss-mgt-console(tss-mgt-console)
        tss-scim
        click tss-scim \"#scim-service\" \"tss-scim\"
    end

    %%Backend dependencies
        tss-scim \-\-> syncope
        %% gateway -.-> tss-scim
        %% gateway -.-> tss-licence
        %% gateway -.-> tss-scheduling
        %% gateway -.-> tss-repository
        %% gateway -.-> tss-account-management
        %% gateway -.-> tss-collaboration
        %% mgmt-console-webapp -.-> gateway
        %% tss-licence -.-> postgresql
        %% tss-logging -.-> decanter-agent
        %% decanter -.-> kafka
        tss-schema-mgmt -.-> schema-registry
        tss-repository -.-> mongodb
        gateway -.-> tss-mgt-console
    
    subgraph Talend Data Streams
        tfd-datastore
        tfd-flowinventory
        tfd-webapp(tfd-webapp)
        tfd-flowrunner>tfd-flowrunner]
        tfd-dataset>tdp-dataset]
    end

    subgraph Talend Streams Runtime
        dag-flowrunner
        beam-jobserver
    end

    %%Datastream dependencies
        gateway -.-> tfd-datastore
        gateway -.-> tfd-flowinventory
        gateway -.-> tfd-flowrunner
        gateway -.-> tss-schema-mgmt
        gateway -.-> tfd-webapp
        gateway -.-> tfd-dataset
        tfd-flowrunner -.-> kafka
        dag-flowrunner \-\-> beam-jobserver
        tfd-flowinventory -.-> kafka
        tfd-flowinventory -.-> tss-repository
        tfd-datastore -.-> kafka
        tfd-datastore -.-> tss-repository
        tfd-flowrunner -.-> dag-flowrunner
        tfd-dataset -.-> tdp-dataset

   subgraph Talend Data Preparation
        tdp-api
        tdp-transformation
        tdp-dataset
        tdp-preparation
        tdp-webapp(tdp-webapp)
    end

    %%Dataprep dependencies
        tdp-api \-\-> tdp-transformation
        tdp-api \-\-> tdp-dataset
        tdp-api \-\-> tdp-preparation
        tdp-transformation \-\-> tdp-dataset
        tdp-transformation \-\-> tdp-preparation
        tdp-transformation -.-> mongodb
        tdp-transformation -.-> tfd-flowrunner        
        tdp-dataset -.-> mongodb
        gateway -.-> tdp-api
        gateway -.-> tdp-webapp


    subgraph Talend Data Stewardship
        tds-data-stewardship-core
        tds-data-history-service
        tds-webapp(tds-webapp)
    end

    %%Data Stewardship dependencies
        tds-data-stewardship-core -.-> kafka
        tds-data-stewardship-core -.-> mongodb
        tds-data-history-service -.-> mongodb
        tds-data-history-service -.-> kafka
        gateway -.-> tds-webapp
        gateway -.-> tds-data-stewardship-core

style tfd-flowrunner fill:#ccf,stroke:#f66,stroke-width:2px,stroke-dasharray: 5, 5;
style tfd-dataset fill:#ccf,stroke:#f66,stroke-width:2px,stroke-dasharray: 5, 5;

</div>

## Services dependencies only

<div class="mermaid">

graph LR

    subgraph Shared Services
        tss-repository
        tss-schema-mgmt
        tss-mgt-console(tss-mgt-console)
        tss-scim
        click tss-scim \"#scim-service\" \"tss-scim\"
    end
    
    subgraph Talend Data Streams
        tfd-datastore
        tfd-flowinventory
        tfd-webapp(tfd-webapp)
        tfd-flowrunner>tfd-flowrunner]
        tfd-dataset>tdp-dataset]
    end

    subgraph Talend Streams Runtime
        dag-flowrunner
        beam-jobserver
    end

    %%Datastream dependencies
        dag-flowrunner \-\-> beam-jobserver
        tfd-flowinventory -.-> tss-repository
        tfd-datastore -.-> tss-repository
        tfd-flowrunner -.-> dag-flowrunner
        tfd-dataset -.-> tdp-dataset

   subgraph Talend Data Preparation
        tdp-api
        tdp-transformation
        tdp-dataset
        tdp-preparation
        tdp-webapp(tdp-webapp)
    end

    %%Dataprep dependencies
        tdp-api \-\-> tdp-transformation
        tdp-api \-\-> tdp-dataset
        tdp-api \-\-> tdp-preparation
        tdp-transformation \-\-> tdp-dataset
        tdp-transformation \-\-> tdp-preparation
        tdp-transformation -.-> tfd-flowrunner        


    subgraph Talend Data Stewardship
        tds-data-stewardship-core
        tds-data-history-service
        tds-webapp(tds-webapp)
    end



style tfd-flowrunner fill:#ccf,stroke:#f66,stroke-width:2px,stroke-dasharray: 5, 5;
style tfd-dataset fill:#ccf,stroke:#f66,stroke-width:2px,stroke-dasharray: 5, 5;

</div>
## Abstraction on top of deployment platform and infrastructure

<div class="mermaid">
%% All
graph LR

    classDef default fill:#3399FF,stroke:#333,stroke-width:2px;

    subgraph Apps
        TDP(Talend Data Preparation)
        TDS(Talend Data Stewardship)
        TDF(Talend Data Stream)
        TIC(Talend Integration Cloud)
    end

    subgraph Infrastructure
        AWS(Amazon Web Services)
        GCE(Google Cloud Engine)
        OpenStack(Open Stack)
        Azure(Microsoft Azure)
        OnPremise(On Premise)
    end

    subgraph Abstraction
        Mesos(Apache Mesos)
        Kubernetes(Kubernetes)
        DEV(Local dev environnment)
        DKR(DKR)
        Puppet
    end

    subgraph Key Software Parts
        RE(Remote Engine)
        Kafka(Apache Kafka)
        ZK(Apache Zookeeper)
    end

    %%TIC Specific
        TIC -.-> DKR
        TIC -.-> RE
        RE -.-> DKR
        RE -.-> OnPremise

    %%TDP Specific
        TDP -.-> DKR

    %%TDS Specific
        TDS -.-> DKR
        TDS -.-> Kafka

    %%TDF Specific
        TDF -.-> DKR
        TDF -.-> Kafka

    %%Key Software Parts specific
        Kafka -.-> DKR
        Kafka -.-> ZK
        ZK -.-> DKR

    %%Mesos Specific
        DKR -.-> Mesos
        DKR -.-> Kubernetes
        DKR -.-> DEV
        Mesos -.-> Puppet
        Kubernetes -.-> Puppet
        Puppet -.-> OnPremise
        Puppet -.-> OpenStack
        Puppet -.-> Azure
        Puppet -.-> GCE
        Puppet -.-> AWS

  


    click Mesos \"http://mesos.apache.org/\" \"Apache Mesos\"

</div>

## Actual deployment scenario

<div class="mermaid">
%% All
graph LR

    classDef default fill:#3399FF,stroke:#333,stroke-width:2px;

    subgraph Applications
        TDP(Talend Data Preparation)
        TDS(Talend Data Stewardship)
        TDF(Talend Data Stream)
        TIC(Talend Integration Cloud)

    end

    subgraph Services Cluster Manager
        AWS(Amazon Web Services)
    end

    subgraph Infrastructure
        EC2(EC2 / ECS)
        OnPremise(On Premise)
    end

    subgraph Beam Abstraction    
        LocalRunner(Local Runner)
        Spark(Apache Spark)
    end

    subgraph Flows Cluster Manager 
        EMR(Elastic MapReduce)
    end

    subgraph Key Software Parts
        RE(Remote Engine)
        Kafka(Apache Kafka)
        ZK(Apache Zookeeper)
    end

    %%TIC Specific
    TIC -.-> AWS
    TIC -.-> RE
    RE -.-> AWS
    RE -.-> OnPremise

    %%TDP Specific
    TDP -.-> AWS
    TDP \-\-> Spark
    TDP \-\-> LocalRunner

    %%TDS Specific
    TDS -.-> AWS
    TDS \-\-> Spark
    TDS \-\-> LocalRunner
    TDS -.-> Kafka

    %%TDF Specific
    TDF -.-> AWS
    TDF \-\-> Spark
    TDF \-\-> LocalRunner
    TDF -.-> Kafka

    %%Key Software Parts specific
    Kafka -.-> AWS
    Kafka -.-> ZK
    ZK -.-> AWS

    %%AWS Related
    AWS -.-> EC2
    EMR -.-> EC2

    %%Framework Specific
    LocalRunner -.-> AWS
    Spark -.-> EMR

</div>
