import React from 'react';
import { StyleSheet, Text, View, TextInput, Button } from 'react-native';

export class BookDetails extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      title: this.props.navigation.state.params.book.title,
      author: this.props.navigation.state.params.book.author,
      nrPages: this.props.navigation.state.params.book.nrPages
    }
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={{flexDirection: 'column', flex: 0.2, width: '80%'}}>
          <TextInput value={this.state.title} onChangeText={(title) => this.setState({title})} />
          <TextInput value={this.state.author} onChangeText={(author) => this.setState({author})} />
          <TextInput value={this.state.nrPages} onChangeText={(nrPages) => this.setState({nrPages})} />
          <Text>{this.props.navigation.state.params.book.ISBN}</Text>
          <Button title="SAVE CHANGES" onPress={() => {
              for(i=0; i<global.bookList.length; i++){
                if(global.bookList[i].ISBN === this.props.navigation.state.params.book.ISBN ){
                  global.bookList[i].title = this.state.title;
                  global.bookList[i].author = this.state.author;
                  global.bookList[i].nrPages = this.state.nrPages;
                }
              }
              this.props.navigation.state.params.updateState();
              this.props.navigation.goBack();
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
