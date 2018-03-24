import 'package:flutter/material.dart';

class MainPage extends StatefulWidget {
  MainPage({Key key, this.title}) : super(key: key);

  static const String routeName = "/MainPage";
  final String title;

  @override
  _MainPageState createState() => new _MainPageState();
}

class _MainPageState extends State<MainPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Overview'),
        actions: <Widget>[
          IconButton(icon: Icon(Icons.home), onPressed: null),
          IconButton(
            icon: Icon(Icons.list),
            onPressed: null,
          ),
          IconButton(icon: Icon(Icons.account_balance), onPressed: null)
        ],
      ),
      body: Material(
        color: Colors.blueAccent,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            InkWell(
                onTap: () => print("Button tapped"),
                child: Container(
                  width: double.infinity,
                  child: Padding(
                    padding: const EdgeInsets.all(20.0),
                    child: Text(
                      '0,0000001',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                          fontSize: 30.0),
                    ),
                  ),
                )
            ),
            Row(
              children: <Widget>[
                Container(
                  child: Text('100 E',
                    style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold,
                        fontSize: 20.0),
                    textAlign: TextAlign.center,
                  ),
                ),
                Container(
                  child: Text('150 \$',
                    style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold,
                        fontSize: 20.0),
                    textAlign: TextAlign.center,),

                ),
                Container(
                  child: Text('750 dkk',
                    style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold,
                        fontSize: 20.0),
                    textAlign: TextAlign.center,)

                ),
              ],
            )
          ],
        ),
      ),
    );
  }
}
