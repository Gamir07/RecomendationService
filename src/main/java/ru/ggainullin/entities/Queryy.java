package ru.ggainullin.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Table(name = "rules")
public class Queryy {

    @Enumerated(EnumType.STRING)
    private QueryType query;
    private String arguments;
    private boolean negate;
}

//Request:
//POST /rule
//
//{
//    "product_name": "Простой кредит", //Имя продукта, который мы рекомендуем
//        "product_id": "ab138afb-f3ba-4a93-b74f-0fcee86d447f", //id продукта, который мы рекомендуем
//        "product_text": "<текст рекомендации>",
//        "rule": [
//    {
//        "query": "USER_OF",
//            "arguments": [
//        "CREDIT"
//            ],
//        "negate": true
//    },
//    {
//        "query": "TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW",
//            "arguments": [
//        "DEBIT",
//                ">"
//            ],
//        "negate": false
//    },
//    {
//        "query": "TRANSACTION_SUM_COMPARE",
//            "arguments": [
//        "DEBIT",
//                "DEPOSIT",
//                ">",
//                "100000"
//            ],
//        "negate": false
//    }
//    ]
