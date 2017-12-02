import React from 'react';
import { AppRegistry, StyleSheet, Text, View, TextInput, Button, ListView } from 'react-native';
import { StackNavigator } from 'react-navigation';
import { BookList } from './Book/BookList'
import { LogIn } from './LogIn/LogIn'
import { BookDetails } from './Book/BookDetails'


const MainNavigator = StackNavigator({
  BookDetails: {screen: BookDetails}
})

const SimpleAppNavigator = StackNavigator({
  Home: {screen: LogIn},
  BookList: {screen: BookList},
  BookDetails: {screen: BookDetails},
});

const AppNavigation = () => (
  <SimpleAppNavigator />
);

global.bookList = [
  {
    title: 'Harry Potter',
    author: 'J.K. Rowling',
    nrPages: '349',
    ISBN: '213124323',
    quotes: [
      {
        text: 'You know nothing Harry Potter',
        addedAt: '05-11-2017'
      }
    ]
  },
  {
    title: 'Harry Potter 2',
    author: 'J.K. Rowling',
    nrPages: '453',
    ISBN: '1232453342',
    quotes: [
      {
        text: 'You must be Harry Potter',
        addedAt: '05-11-2017'
      }
    ]
  },
  {
    title: 'The Pragmatic Programmer',
    author: 'Multiple',
    nrPages: '349',
    ISBN: '213124323',
    quotes: [
      {
        text: 'Clean code',
        addedAt: '05-11-2017'
      }
    ]
  }
];
global.ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});

export default class App extends React.Component{
  render() {
    return (
      <AppNavigation style={styles.container}/>
    );
  }
}


const styles = StyleSheet.create({
  container: {
    flex: 0.5,
    backgroundColor: '#fff',
    justifyContent: 'center',
    padding: 1,
  },
});
