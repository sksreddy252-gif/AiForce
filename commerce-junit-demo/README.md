# Commerce JUnit Demo

This project is a larger Java + Maven commerce sample designed to mirror common retail commerce workflows such as catalog management, cart operations, promotions, inventory checks, and checkout processing.

## Project structure

```text
commerce-junit-demo/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/java/com/example/commerce/
    │   ├── cart/
    │   │   ├── CartItem.java
    │   │   └── ShoppingCart.java
    │   ├── domain/
    │   │   ├── Category.java
    │   │   ├── Customer.java
    │   │   └── Product.java
    │   ├── inventory/
    │   │   └── InventoryService.java
    │   ├── order/
    │   │   ├── CheckoutService.java
    │   │   └── Order.java
    │   ├── payment/
    │   │   ├── PaymentResult.java
    │   │   └── PaymentService.java
    │   ├── pricing/
    │   │   └── PricingService.java
    │   ├── promotion/
    │   │   ├── Promotion.java
    │   │   └── PromotionEngine.java
    │   ├── service/
    │   │   └── CommerceService.java
    │   └── util/
    │       └── MoneyUtils.java
    └── test/java/com/example/commerce/
        ├── CheckoutServiceTest.java
        ├── InventoryServiceTest.java
        ├── PromotionEngineTest.java
        └── ShoppingCartTest.java
```

## Features
- Product and category model
- Customer profile support
- Shopping cart with item quantities and subtotal calculations
- Inventory checks and stock validation
- Discount and promotion engine
- Pricing service with shipping logic
- Checkout process with order creation
- Payment processing stub and result object
- JUnit 5 tests covering the common flow

## Run tests

```bash
cd commerce-junit-demo
mvn test
```

## Notes
This is a public sample for learning and demonstration. It is not directly tied to HCL Commerce, but it follows the patterns used in many real-world commerce applications.
