import React from 'react';
import { StyleSheet, Text, View, TextInput } from 'react-native';
import { StackNavigator } from 'react-navigation';


const MainNavigator = StackNavigator({
  BookDetails: {screen: BookDetails}
})

export default class App extends React.Component {

  render() {
    return <BookList />;
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    justifyContent: 'center',
    padding: 1,
  },
});
