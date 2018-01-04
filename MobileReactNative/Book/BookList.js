import React from 'react';
import { StyleSheet, Text, View, TextInput, ListView, TouchableHighlight, TouchableOpacity, Button, AsyncStorage } from 'react-native';
import {StackNavigator} from 'react-navigation';
import { BookDetails } from './BookDetails';

export class BookList extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      ds: global.ds.cloneWithRows([]),
      uid: this.props.navigation.state.params.uid
    }
    this.ref = global.firebaseApp.database().ref().child('users').child(this.state.uid).child('books');
    this.auth = global.firebaseApp.auth();
    this.updateState();
  }

  
  updateState(){
    this.ref.on('value', (snap) => {
      var bookList = [];
      snap.forEach(element => {
        bookList.push(element.val())
      });
      console.log(bookList);
      this.setState({ds: global.ds.cloneWithRows(bookList)});
    })
  }

  renderRow(record){
    return (
      <View>
        <TouchableOpacity onPress={() => this.props.navigation.navigate('BookDetails', {book: record, updateState: this.updateState.bind(this)})}>
          <View style={{flexDirection: 'row', padding: 10}}>
            <View stle={{flex: 1}}>
              <Text>{record.title}</Text>
            </View>
            <View style={{flex: 1}}>
            <Text style={{textAlign: 'right'}}>{record.nrPages}</Text>
            </View>
          </View>
        </TouchableOpacity>
      </View>
    );
  }

  render() {
    return (
      <View>
        <ListView
          dataSource={this.state.ds}
          renderRow={this.renderRow.bind(this)}
        />
        <Button title="ADD" onPress={() => this.props.navigation.navigate('BookNew', {updateState: this.updateState.bind(this)})}/>
        <Button title="LOG OUT" onPress={() => {
          this.auth.signOut()
          this.props.navigation.goBack();
        }}/>
      </View>
    );
  }
}



const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
