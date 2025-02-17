package io.rsg.cqrs.repository;

import io.rsg.cqrs.entity.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ElasticsearchRepository<Order,  String> {
}
