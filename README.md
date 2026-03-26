# 🚀 Enterprise API Test Automation Framework (Kurumsal API Test Otomasyon Mimarisi)

## 📌 Overview (Genel Bakış)
This project is a robust, enterprise-level API test automation framework built to seamlessly validate backend services and ensure data integrity.
*(Bu proje, arka uç servislerini sorunsuz bir şekilde doğrulamak ve veri bütünlüğünü sağlamak için inşa edilmiş kurumsal seviyede bir API test otomasyon mimarisidir.)*

## 🛠️ Tech Stack (Kullanılan Teknolojiler)
* **Language (Dil):** Java 17+
* **API Client (İstemci):** RestAssured
* **Test Runner (Çalıştırıcı):** TestNG
* **Build Tool (İnşa Aracı):** Maven
* **Architecture (Mimari):** POJO (Plain Old Java Object)
* **Validation (Doğrulama):** Hamcrest Matchers & JSON Schema Validator

## ⚙️ Key Features & Milestones (Temel Özellikler ve Başarılar)
* **Full CRUD Operations:** Automated `GET`, `POST`, `PUT`, and `DELETE` requests with self-cleaning capabilities (İz bırakmayan `DELETE` operasyonları ile tam CRUD döngüsü).
* **Data-Driven Testing (DDT):** Dynamic payload injection using TestNG `@DataProvider` to run hundreds of iterations with zero hardcoded data (Sıfır sabit veri ile TestNG `@DataProvider` üzerinden dinamik veri enjeksiyonu).
* **POJO Architecture:** Secure Serialization and Deserialization of JSON payloads for ultimate type safety (Maksimum tip güvenliği için JSON verilerinin güvenli Serileştirme ve Deserileştirme işlemleri).
* **Contract Testing:** Strict validation of API architecture using RestAssured JSON Schema Validator (RestAssured JSON Şablon Doğrulayıcı kullanarak API mimarisinin katı bir şekilde doğrulanması).
* **Dynamic Authorization:** Handling tokens and intelligently bypassing security walls like `401 Unauthorized` and `403 Forbidden` (Dinamik token yönetimi ve güvenlik duvarlarının aşılması).

## 🚀 How to Run (Nasıl Çalıştırılır)
1. Clone the repository. *(Projeyi bilgisayarınıza indirin.)*
2. Update Maven dependencies via `pom.xml`. *(pom.xml üzerinden Maven kütüphanelerini güncelleyin.)*
3. Run the TestNG suite or execute individual test classes under `src/test/java/apiTests`. *(TestNG paketini veya bireysel test sınıflarını çalıştırın.)*