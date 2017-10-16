# aws-reinvent-csv
I want csv file of re:Invent catalog.

# usage
1. カタログのHTMLを取得
re:InventにログインしてiMacroにmacro.expand-catalog.iimを読み込ませ実行するとスケジュールが全部展開されたカタログが開きます。

開発者ツールか何かでid="searchResult"以下のHTMLを取得します。

2. Gradleプロジェクトとしてプロジェクトを読み込んで依存性を解決

3. parser.Mainにoutput（CSV出力先）とinput（HTML）を指定
以下★を編集
```java
		//output
		CSVWriter writer = new CSVWriter(new FileWriter(new File("★出力先ディレクトリパス", "★出力ファイル名")));
		//input
		Path path = FileSystems.getDefault().getPath("★1で作ったHTMLファイルの配置先ディレクトリ", "★ファイル名");
```

4. Mainクラスを実行すると3で指定された出力先にCSVファイルが出力されます。
