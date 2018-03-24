import 'package:flutter/material.dart';

void main() => runApp(new MyApp());

final MyDrawer _drawer = new MyDrawer();
var routes = <String, WidgetBuilder>{
  Workers.routeName: (BuildContext context) => new Workers(title: 'Workers'),
  Overview.routeName: (BuildContext context) => new Overview(title: 'Overview'),
  TemplateTest.routeName: (BuildContext context) => new TemplateTest(title: 'TemplateTest')
};

class MyDrawer extends StatefulWidget {
  @override
  _MyDrawerState createState() => new _MyDrawerState();
}

class _MyDrawerState extends State<MyDrawer> {
  @override
  Widget build(BuildContext context) {
    return new Drawer(
      child: new ListView(
        children: <Widget>[
          new DrawerHeader(
              child: new Text('Header')
          ),
          new ListTile(
            leading: new Icon(Icons.home),
            title: new Text('Workers'),
            onTap: (){
              Navigator.of(context)
                  ..pop()
                  ..pushNamed(Workers.routeName);
            },
          ),
          new ListTile(
            leading: new Icon(Icons.arrow_back),
            title: new Text('Home'),
            onTap: (){
              Navigator.of(context)
                  ..pop()
                  ..pushNamed(Overview.routeName);
            },
          )
        ],
      ),

    );
  }
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'JMP Stats',
      theme: new ThemeData(
        primarySwatch: Colors.red,
      ),
      home: new Overview(),
      routes: routes,
    );
  }
}

class Overview extends StatefulWidget {
  Overview({Key key, this.title}) : super(key:key);

  static const String routeName = '/Overview';

  final String title;

  @override
  _OverviewState createState() => new _OverviewState();
}

class _OverviewState extends State<Overview> {
  List<String> _transactions = <String>[];

  @override
  void initState() {
    super.initState();
    _transactions = new List.generate(10, (i) => 'Transaction $i');
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      drawer: _drawer,
      appBar: new AppBar(
        title: new Text('Overview'),
      ),
      body: new ListView(
        children: _transactions.map((transaction) => new Text(transaction)).toList(),
      ),
    );
  }
}

class Workers extends StatefulWidget{
  Workers({Key key, this.title}) : super(key:key);

  static const String routeName = "/Workers";

  final String title;

  @override
  WorkersState createState() => new WorkersState();

}

class WorkersState extends State<Workers>{
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      drawer: _drawer,
      appBar: new AppBar(
        title: new Text('Workers'),
      ),
      body: new Align(
        alignment: Alignment.bottomRight,
        child: new FloatingActionButton(onPressed: null),
      ),
    );
  }
}

class TemplateTest extends StatefulWidget {
  TemplateTest({Key key, this.title}) : super(key: key);

  static const String routeName = "/TemplateTest";

  final String title;

  @override
  _TemplateTestState createState() => new _TemplateTestState();
}

class _TemplateTestState extends State<TemplateTest> {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text(widget.title),
      ),
      body: new Container(),
      floatingActionButton: new FloatingActionButton(
        onPressed: _onFloatingActionButtonPressed,
        tooltip: 'Add',
        child: new Icon(Icons.add),
      ),
    );
  }

  void _onFloatingActionButtonPressed(
      ) {
  }
}