import React from 'react';
import { StyleSheet, Text, View, TextInput, ListView, TouchableHighlight, TouchableOpacity } from 'react-native';
import {StackNavigator} from 'react-navigation';
import { BookDetails } from './BookDetails';

export class BookList extends React.Component {

  constructor() {
    super();
    this.state = {
      ds: global.ds.cloneWithRows(global.bookList)
    }
  }

  renderRow(record){
    return (
      <View>
        <TouchableOpacity onPress={() => this.props.navigation.navigate('BookDetails', {book: record})}>
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
      <ListView
        dataSource={this.state.ds}
        renderRow={this.renderRow.bind(this)}
      />
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
