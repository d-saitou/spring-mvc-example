# Spring4 MVCサンプルアプリケーション
\* [英語版](/README.md)

## 1. 概要
本プロジェクトは、Spring Frameworkを利用したWebアプリケーションサンプルのMavenプロジェクトです。

## 2. 開発環境
本プロジェクトは以下環境で開発しています。

* IDE
  - Eclipse 4.6 Neon
  - Spring Tool Suite (STS) 3.8.4 (Eclipseプラグイン)
  - Lombok 1.16.12
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

## 4. 実行方法
本アプリケーションを実行する場合、以下の手順を実施してください。

### 4.1. テスト環境
[provisioning-environment-for-tomcat8](https://github.com/d-saitou/provisioning-environment-for-tomcat8) を参照してください。

### 4.2. 開発環境
以下の手順を実施してください。

1. 以下ソフトウェアをインストールしてください。
	* [Eclipse](https://www.eclipse.org/) ※[Pleiades All in One](http://mergedoc.osdn.jp/) でも可。
	* [MySQL 5.6以降](https://www.mysql.com/)
	* [Tomcat 8以降](http://tomcat.apache.org/) ※Eclipseと連携する設定を実施してください。


2. Eclipse に以下のプラグインをインストールしてください。
	* Spring Tool Suite (STS)
	* [Lombok](https://projectlombok.org/) ※インストール手順についてはURLを参照してください。


3. Eclipse上で本ソースプロジェクトをチェックアウトしてください。

4. 以下コマンドを実行して、MySQLにデータベースを作成してください。

  ```
  mysql -u {MySQLユーザー} -p{パスワード} < {本ソースプロジェクトルート}/data/db/spring4example.sql
  ```

5. 必要に応じて、設定ファイル [web.xml](/src/main/webapp/WEB-INF/web.xml) の以下パラメータを変更してください。  

| パラメータ                                                   | 説明                 |
|:-------------------------------------------------------------|:---------------------|
| &lt;servlet&gt;\-&lt;multipart\-config&gt;\-&lt;location&gt; | 一時ファイル格納場所 |

6. 必要に応じて、設定ファイル [application.properties](/src/main/resources/application.properties) の以下パラメータを変更してください。  

| パラメータ    | 説明                                   |
|:--------------|:---------------------------------------|
| app.datadir   | ファイル格納場所(ログ・一時ファイル他) |
| jdbc.url      | 接続先DB指定                           |
| jdbc.username | DB接続時ユーザ名                       |
| jdbc.password | DB接続時パスワード                     |
| javax.mail.\* | メール送信設定                         |

7. Eclipse上で、本ソースプロジェクトをTomcatで実行できるように設定してください。
