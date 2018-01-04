import React from 'react';
import { StyleSheet, Text, View, TextInput, Button, AsyncStorage, processColor } from 'react-native';
import DatePicker from 'react-native-datepicker'
import { Bar } from 'react-native-pathjs-charts'

export class BookDetails extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      title: this.props.navigation.state.params.book.title,
      author: this.props.navigation.state.params.book.author,
      nrPages: this.props.navigation.state.params.book.nrPages,
      endDate: this.props.navigation.state.params.book.endDate
    }
    this.auth = global.firebaseApp.auth();
    this.auth.onAuthStateChanged((user) => {
      if(user){
        this.dbRef = global.firebaseApp.database().ref().child('users').child(user.uid).child('books')
      }
    })
  }


  render() {
    options = {
      width: 300,
      height: 300,
      margin: {
        top: 20,
        left: 25,
        bottom: 50,
        right: 20
      },
      color: '#2980B9',
      gutter: 20,
      animate: {
        type: 'oneByOne',
        duration: 200,
        fillTransition: 3
      },
      axisX: {
        showAxis: true,
        showLines: true,
        showLabels: true,
        showTicks: true,
        zeroAxis: false,
        orient: 'bottom',
        label: {
          fontFamily: 'Arial',
          fontSize: 8,
          fontWeight: true,
          fill: '#34495E',
          rotate: 45
        }
      },
      axisY: {
        showAxis: true,
        showLines: true,
        showLabels: true,
        showTicks: true,
        zeroAxis: false,
        orient: 'left',
        label: {
          fontFamily: 'Arial',
          fontSize: 8,
          fontWeight: true,
          fill: '#34495E'
        }
      }
    }
    data = []
    intNrPages = parseInt(this.state.nrPages)
    date1 = new Date(this.state.endDate);
    date2 = new Date();
    timeDiff = Math.abs(date1.getTime() - date2.getTime());
    diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    for(i=0;i<diffDays;i++){
      x = Math.random() * intNrPages;  
      intNrPages -= x;
      data.push([{day: i, pages: x}]);
    }
    return (
      <View style={styles.container}>
        <View style={{flexDirection: 'column', flex: 0.2, width: '80%'}}>
          <Text>{this.props.navigation.state.params.book.isbn}</Text>
          <TextInput value={this.state.title} onChangeText={(title) => this.setState({title})} />
          <TextInput value={this.state.author} onChangeText={(author) => this.setState({author})} />
          <TextInput value={this.state.nrPages} onChangeText={(nrPages) => this.setState({nrPages})} />
          <DatePicker date={this.state.endDate} 
              mode="date"
              placeholder="select date"
              onDateChange={(date) => {this.setState({endDate: date})}}
          />
          <Button title="SAVE" onPress={() => {
            this.dbRef.child(this.props.navigation.state.params.book.isbn).set({
                isbn: this.props.navigation.state.params.book.isbn, 
                title: this.state.title, 
                author: this.state.author, 
                nrPages: this.state.nrPages, 
                endDate: this.state.endDate
            }).then(() => {
                this.props.navigation.state.params.updateState();
                this.props.navigation.goBack();
            });
            }
          }
          />
          <Button title="REMOVE" color='#FF0000' onPress={() => {
              this.dbRef.child(this.props.navigation.state.params.book.isbn).remove().then(() => {
                this.props.navigation.state.params.updateState();
                this.props.navigation.goBack();
              });
            }
          }
          />
          <Bar 
            data={data} options={options} accessorKey='pages'
          />
        </View>
      </View>
    );
  }
}



const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  chart: {
    flex: 1
  }
});
