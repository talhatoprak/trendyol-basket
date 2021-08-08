package com.trendyol.product.repository;

import com.couchbase.client.java.query.QueryScanConsistency;
import com.trendyol.product.entity.Product;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.ScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CouchbaseRepository<Product,String> {
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Optional<Product> findById(String id);
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    void deleteByBarcode(String barcode);
    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    boolean existsByBarcode(String barcode);

    @ScanConsistency(query = QueryScanConsistency.REQUEST_PLUS)
    Product findByBarcode(String barcode);
}
