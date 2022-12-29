# 最終講課題提出用リポジトリ

# APIの概要
以下に記載いたします。

## Create
1. 患者データの作成(DB1に対して)
2. お薬データの挿入（DB2に対して）

## Read
1. 患者データ一覧の取得（DB1全件取得）
2. 各患者のお薬手帳データの取得（患者のDB2を全件取得）

## Update
1. お薬手帳データの修正（薬局名、処方名）

## Delete
1. 登録解除された患者のデータを削除（DB 1,DB2)


# データベース定義

DB1:各患者データのDB
| カラム名（論理名） | カラム名（物理名） | 型・桁       | Nullable | その他コメント | 
| ------------------ | ------------------ | ------------ | -------- | -------------- | 
| ID                 | id                 | int          | NO       | primary key    | 
| 名前               | name               | varchar(255) | NO       |                | 
| 生年月日           | birthdate          | date         | NO       | YYYY-MM-DD形式 | 

DB2〜：各患者のお薬手帳本体DB（登録患者分作成される）
| カラム名（論理名） | カラム名（物理名） | 型・桁       | Nullable | その他コメント                           | 
| ------------------ | ------------------ | ------------ | -------- | ---------------------------------------- | 
| ID                 | id                 | int          | NO       | primary key                              | 
| 訪問日             | visitDate          | date         | NO       | YYYY-MM-DD形式<br>入力日当日を入力したい | 
| 薬局名             | pharmacy           | varchar(255) | NO       | 外部APIから取ってこれないかな？          | 
| 処方薬             | medicine           | varchar(255) | NO       | 外部APIから取ってこれないかな？          | 
