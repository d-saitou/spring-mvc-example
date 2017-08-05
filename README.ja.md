# Spring4 MVCサンプルアプリケーション
\* [英語版](/README.md)

## 1. 概要
本プロジェクトは、Spring Frameworkを利用したWebアプリケーションサンプルのMavenプロジェクトです。

## 2. 開発環境
本プロジェクトは以下環境で開発しています。

* IDE
  - Eclipse 4.6 Neon
  - Spring Tool Suite(STS) 3.8.4 (Eclipseプラグイン)
  - Lombok 1.16.12 (Eclipseインストール済み)
* DB
  - MySQL 5.6

## 3. 機能一覧
本アプリケーションでは以下の機能を実装しています。

* 基本機能
  - 認証・認可
  - バリデーション
  - ファイルアップロード・ダウンロード
  - DB-CRUD
  - 静的リソース参照 (Webjars利用)
  - 例外処理
  - 国際化
* AOP (トランザクション設定, ロギング)
* REST API (テキスト, XML, JSON)
* 非同期処理 (メール送信)
* タスク (タスク実行履歴DB登録)

## 4. 注意点
本アプリケーションを実行する場合、以下の設定が必要です。

### 4.1. 実行環境
以下ソフトウェアをインストールしてください。

* Application server (Tomcat等)
* MySQL
* Lombok (開発環境のみ。必須)
* Spring Tool Suite (開発環境のみ。必要に応じて)

### 4.2. アプリケーション設定変更
設定ファイルの以下パラメータを変更してください。

* [application.properties](/src/main/resources/application.properties)

| パラメータ    | 説明                                   |
|:--------------|:---------------------------------------|
| app.datadir   | ファイル格納場所(ログ・一時ファイル他) |
| jdbc.url      | 接続先DB指定                           |
| jdbc.username | DB接続時ユーザ名                       |
| jdbc.password | DB接続時パスワード                     |
| javax.mail.\* | メール送信設定                         |

* [web.xml](/src/main/webapp/WEB-INF/web.xml)

| パラメータ                                | 説明                 |
|:------------------------------------------|:---------------------|
| <servlet>\-<multipart-config>\-<location> | 一時ファイル格納場所 |

### 4.3. ビルド及びデプロイ
開発環境でビルドを行い、アプリケーションをデプロイしてください。

### 4.4. データベース作成
MySQLにデータベースを作成するため、以下のコマンドで[SQL](/data/db/spring4example.sql)を実行してください。

  ```
  mysql -u [ユーザ] -p[パスワード] < [プロジェクトルート]/data/db/spring4example.sql
  ```
