package com.erent.service;

public interface CostService {

    /**
     * 添加费用账单
     * @param arrearsId
     * @param cost
     */
    void generateExpenseBill(int arrearsId, int cost);
}
