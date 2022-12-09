package com.scoring.infrastructure;

import io.confluent.ksql.api.client.Client;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class KafkaDatabaseUtils {

    public static final String CREATE_PAYMENTS_STREAM_SQL = """
            CREATE STREAM IF NOT EXISTS PAYMENTS
            (paymentId VARCHAR KEY, accountId VARCHAR, balance VARCHAR, daysOfDelays VARCHAR, overdueAmount VARCHAR, accountStatus VARCHAR, paymentsCountAggregator VARCHAR)
            WITH (kafka_topic='payments-history', value_format='json');
            """;
    public static final String CREATE_PAYMENTS_METRICS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS PAYMENTS_METRICS AS
             SELECT PAYMENTSCOUNTAGGREGATOR, COUNT(*) as PAYMENTS_COUNT
                FROM PAYMENTS
                GROUP BY PAYMENTSCOUNTAGGREGATOR
                EMIT CHANGES;
            """;
    public static final String CLIENTS_STREAM_SQL = """
                    CREATE STREAM IF NOT EXISTS CLIENTS (clientId VARCHAR KEY)
                    WITH (kafka_topic='clients', value_format='json');
            """;
    public static final String ACCOUNTS_STREAM_SQL = """
                    CREATE STREAM IF NOT EXISTS ACCOUNTS (accountId VARCHAR KEY, clientId VARCHAR, accountType VARCHAR)
                    WITH (kafka_topic='accounts', value_format='json');
            """;

    public static final String CREATE_CLIENT_SUMMARIES_STREAM_SQL = """
            CREATE STREAM IF NOT EXISTS CLIENT_SUMMARIES
            (summaryId VARCHAR KEY, clientId VARCHAR, accountTypes VARCHAR, sumOfBalances VARCHAR, worstStatus VARCHAR, maxDelayedDays VARCHAR, creationDate VARCHAR, maxOverdueAmount VARCHAR, lastStatues VARCHAR, summariesAggregator VARCHAR)
            WITH (kafka_topic='client-summary', value_format='json');
            """;

    public static final String CREATE_CLIENT_SUMMARIES_METRICS_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS CLIENT_SUMMARIES_METRICS AS
             SELECT SUMMARIESAGGREGATOR, COUNT(*) as CLIENT_SUMMARIES_COUNT
                FROM CLIENT_SUMMARIES
                GROUP BY SUMMARIESAGGREGATOR
                EMIT CHANGES;
            """;

    @Inject
    private Client kafkaDatabaseClient;

    public void createPaymentsStream() {
        try {
            kafkaDatabaseClient.executeStatement(CREATE_PAYMENTS_STREAM_SQL).get();
            kafkaDatabaseClient.executeStatement(CREATE_PAYMENTS_METRICS_TABLE_SQL).get();
            kafkaDatabaseClient.executeStatement(CLIENTS_STREAM_SQL).get();
            kafkaDatabaseClient.executeStatement(ACCOUNTS_STREAM_SQL).get();
            kafkaDatabaseClient.executeStatement(CREATE_CLIENT_SUMMARIES_STREAM_SQL).get();
            kafkaDatabaseClient.executeStatement(CREATE_CLIENT_SUMMARIES_METRICS_TABLE_SQL).get();
        } catch (Exception e) {
            log.error("Exception occurred during database integration, closing the application :(");
            log.error(e.getMessage());
            System.exit(-1);
        }
    }

    public void deleteAllTablesAndStreams() {
        kafkaDatabaseClient.executeStatement("DROP TABLE IF EXISTS PAYMENTS_METRICS;").join();
        kafkaDatabaseClient.executeStatement("DROP TABLE IF EXISTS CLIENT_SUMMARIES_METRICS;").join();
        kafkaDatabaseClient.executeStatement("DROP STREAM IF EXISTS CLIENT_SUMMARIES;").join();
        kafkaDatabaseClient.executeStatement("DROP STREAM IF EXISTS PAYMENTS;").join();
        kafkaDatabaseClient.executeStatement("DROP STREAM IF EXISTS CLIENTS;").join();
        kafkaDatabaseClient.executeStatement("DROP STREAM IF EXISTS ACCOUNTS;").join();
    }

}
