# Spring MVCサンプルアプリケーション
\* [英語版](/README.md)

## 1. 概要
本プロジェクトは、Spring Frameworkを利用したWebアプリケーションサンプルのMavenプロジェクトです。

## 2. 実装
実装例として以下の機能を実装しています。

* 入力フォームバリデーション
* ファイルアップロード・ダウンロード
* DB CRUD
* 認証・認可
* AOP (トランザクション設定、ロギング)
* REST API
* メール送信 (非同期使用)
* 静的リソース参照 (Webjars利用)
* 例外処理
* 国際化
* タスクスケジューラー

## 3. 開発環境
本アプリケーションを実行する場合、以下の手順を実施してください。

1. 以下をインストールしてください。
	* JDK 11 以降
	* Eclipse や IntelliJ 等の IDE (Lombok・Mavenプラグインが必要)
	* MySQL 8.0
	* Tomcat 9 以降

2. IDE上で本ソースプロジェクトをチェックアウトしてください。

3. 必要に応じて、設定ファイル [application.properties](/src/main/resources/application.properties) の以下パラメータを変更してください。  

| パラメータ          | 説明                                   |
|:--------------------|:---------------------------------------|
| application.datadir | ファイル格納場所(ログ・一時ファイル他) |
| jdbc.host           | データベースホスト                     |
| jdbc.database       | データベース名                         |
| jdbc.username       | データベースユーザ名                   |
| jdbc.password       | データベースパスワード                 |
| javax.mail.\*       | メール送信設定                         |

4. [application.properties](/src/main/resources/application.properties) のパラメータに従って、MySQLにデータベースとユーザーを作成してください。

5. (Windows) バッチファイル[run_sql_files.bat](/data/db/run_sql_files.bat)\*を実行して、MySQLにテーブルを作成してください。  
   (その他) [db](/data/db)ディレクトリ配下のSQLファイルを実行して、MySQLにテーブルを作成してください。  
   \*[run_sql_files.bat](/data/db/run_sql_files.bat) は [application.properties](/src/main/resources/application.properties) を参照してデータベースに接続します。

6. 本ソースプロジェクトをTomcatにデプロイしてください。
