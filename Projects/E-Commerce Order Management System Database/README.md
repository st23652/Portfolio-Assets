# Relational Database Architecture & SQL Management 🗄️

## Overview
This project involves the end-to-end design, normalization, and implementation of a relational database for an order management system. It showcases the transformation of a flat, unnormalized dataset into a structured, multi-table schema that eliminates data redundancy and preserves data integrity. The project bridges theoretical database mathematics with practical SQL scripting.

## Normalization & Database Theory
The foundation of this architecture relies on strict mathematical proofs to define table relationships and constraints:
* **Functional Dependency Analysis:** The system's primary keys were mathematically deduced by calculating attribute closures (e.g., proving that $\xi^{+}=\{OrderID+ProductID\}$ determines all other attributes).
* **Dependency Resolution:** The database structure was refined by explicitly identifying and resolving Full Key, Partial, and Transitive Functional Dependencies (e.g., resolving the transitive dependency where `CustomerID` determines `CustomerName`).
* **Lossless Join Verification:** The decomposed schema was rigorously tested to ensure data consistency. Using relational algebra, the intersections (e.g., $R_1 \cap R_3 = \{C\}$) and unions (e.g., $R_1 \cup R_2$) of table attributes were verified to guarantee that no data is lost or incorrectly generated during table joins.

## SQL Implementation
The theoretical design was successfully implemented using standard SQL:
* **Schema Creation:** Developed table structures (`Customers`, `Orders`, `Products`, `TotalAmount`, `Person`) enforcing primary key constraints and establishing foreign key relationships to maintain referential integrity.
* **Data Population:** Utilized `INSERT INTO` statements to populate the relational tables with a sample dataset of customers, inventory, and order records.

## Advanced Querying
The project features complex SQL queries designed to extract meaningful business intelligence from the normalized tables:
* **Aggregation & Grouping:** Calculated the total quantity of each product sold across the database using `SUM()` and `GROUP BY` clauses.
* **Multi-Table Joins:** Retrieved comprehensive customer order summaries and total prices by `JOIN`ing the `Orders`, `TotalAmount`, and `Customers` tables.
* **Data Ranking:** Identified the top 3 highest-spending customers by combining aggregation functions with `ORDER BY Total DESC` and `LIMIT 3` constraints.