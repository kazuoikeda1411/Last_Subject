# 最終講課題提出用リポジトリ

# アプリケーションの概要、起動手順、APIの動作確認用コマンド
以下に記載いたします。

### Create
1. 患者データの作成(DB1に対して)

　　　POST：/patients

　　　![Pasted Graphic 68](https://user-images.githubusercontent.com/118358124/219021952-08dad6ef-87b7-4382-8bed-753271ba1237.png)

　　　生年月日（birthdate）が８桁でない場合

　　　![Pasted Graphic 72](https://user-images.githubusercontent.com/118358124/219022012-9684a335-54ee-4d7c-8c79-1f4e51afd22f.png)

2. お薬データの挿入（DB2に対して）

　　　POST：/medicines

　　　![Pasted Graphic 69](https://user-images.githubusercontent.com/118358124/219022352-7627778b-cf57-4123-b1c2-68fb3e28113a.png)

　　　生年月日（birthdate）が８桁でない場合

　　　![Pasted Graphic 73](https://user-images.githubusercontent.com/118358124/219022426-22a9edc9-c392-46be-9003-93d693e9e689.png)

### Read
1. 患者データ一覧の取得（DB1全件取得）

　　　GET：/patients

　　　![Pasted Graphic 66](https://user-images.githubusercontent.com/118358124/219022645-6ca3fa2d-00dc-42ac-84d4-d671219ab0d7.png)

2. 各患者のお薬手帳データの取得（患者のDB2を全件取得）

　　　GET：/medicines

　　　![Pasted Graphic 70](https://user-images.githubusercontent.com/118358124/219022794-f03f8bf8-5c21-47d4-84cc-38a403ba7640.png)

　　　DBに合致する患者がいない場合

　　　![Pasted Graphic 71](https://user-images.githubusercontent.com/118358124/219022984-4345928d-1e02-40ea-8dd8-f6b575c8375c.png)

### Update(後日コード作成予定）
1. お薬手帳データの修正（薬局名、処方名）

### Delete(後日コード作成予定）
1. 登録解除された患者のデータを削除（DB 1,DB2)

# データベース定義

DB1:各患者データのDB
| カラム名（論理名） | カラム名（物理名） | 型・桁       | Nullable | その他コメント | 
| ------------------ | ------------------ | ------------ | -------- | -------------- | 
| ID                 | id                 | int          | NO       | primary key    | 
| 名前               | name               | VARCHAR(255) | NO       | DB2を引用する際に使用               | 
| 生年月日           | birthdate          | DATE         | NO       | YYYY-MM-DD　DB2を引用する際に使用                | 

DB2：各患者の処方データ
| カラム名（論理名） | カラム名（物理名） | 型・桁       | Nullable | その他コメント                           | 
| ------------------ | ------------------ | ------------ | -------- | ---------------------------------------- | 
| ID                 | id                 | int          | NO       | primary key                              | 
| ユーザーID          | userId             | int          | NO       |  DB1のIDに対応している                       | 
| 訪問日             | visitDate          | datetime     | NO       | YYYY-MM-DD HH:MM:SS(登録時間) | 
| 薬局名             | pharmacy           | VARCHAR(255) | NO       |                                           | 
| 処方薬             | medicine           | VARCHAR(255) | NO       |                                            | 
