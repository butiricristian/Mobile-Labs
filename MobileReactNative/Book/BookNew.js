import React from 'react';
import { StyleSheet, Text, View, TextInput, Button, AsyncStorage } from 'react-native';
import DatePicker from 'react-native-datepicker'


export class BookNew extends React.Component {

  constructor(props){
    super(props);
    this.state = {
        isbn: "",
        title: "",
        author: "",
        nrPages: "",
        endDate: new Date()
    }
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={{flexDirection: 'column', flex: 0.2, width: '80%'}}>
          <TextInput placeholder="ISBN" onChangeText={(isbn) => this.setState({isbn})} ></TextInput>
          <TextInput placeholder="Title" onChangeText={(title) => this.setState({title})} />
          <TextInput placeholder="Author" onChangeText={(author) => this.setState({author})} />
          <TextInput placeholder="nrPages" onChangeText={(nrPages) => this.setState({nrPages})} />
          <DatePicker date={this.state.endDate} 
              mode="date"
              placeholder="select date"
              onDateChange={(date) => {this.setState({endDate: date})}}
          />
          <Button title="SAVE" onPress={() => {
                AsyncStorage.setItem(this.state.isbn, JSON.stringify({
                    ISBN: this.state.isbn, 
                    title: this.state.title, 
                    author: this.state.author, 
                    nrPages: this.state.nrPages, 
                    endDate: this.state.endDate
                })).then(() => {
                    this.props.navigation.state.params.updateState();
                    this.props.navigation.goBack();
                });
            }
          }
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
    justifyContent: 'center',
  },
});
