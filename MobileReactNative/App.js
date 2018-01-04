import React from 'react';
import { AppRegistry, StyleSheet, Text, View, TextInput, Button, ListView } from 'react-native';
import { StackNavigator } from 'react-navigation';
import { BookList } from './Book/BookList'
import { LogIn } from './LogIn/LogIn'
import { SignUp } from './LogIn/SignUp'
import { BookDetails } from './Book/BookDetails'
import { BookNew } from './Book/BookNew'
import * as firebase from 'firebase'
import * as PushNotification from 'react-native-push-notification'


const MainNavigator = StackNavigator({
  BookDetails: {screen: BookDetails}
})

const SimpleAppNavigator = StackNavigator({
  Home: {screen: LogIn},
  BookList: {screen: BookList},
  BookDetails: {screen: BookDetails},
  BookNew: {screen: BookNew},
  SignUp: {screen: SignUp},
});

const AppNavigation = () => (
  <SimpleAppNavigator />
);

const firebaseConfig = {
  apiKey: "AIzaSyAGR2fBXKj4ftMg0KveMnOYS8junmeQWlw",
  authDomain: "mobileapp-5f098.firebaseapp.com",
  databaseURL: "https://mobileapp-5f098.firebaseio.com/",
  storageBucket: "mobileapp-5f098.appspot.com",
  messagingSenderId: "840579237111"
};
global.firebaseApp = firebase.initializeApp(firebaseConfig);

global.bookList = [
  {
    title: 'Harry Potter',
    author: 'J.K. Rowling',
    nrPages: '349',
    ISBN: '213124323',
    endDate: new Date()
  },
  {
    title: 'Harry Potter 2',
    author: 'J.K. Rowling',
    nrPages: '453',
    ISBN: '1232453342',
    endDate: new Date()
  }
];
global.ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});

export default class App extends React.Component{

  notifications(){
    // const messaging = firebase.messaging();
    // messaging.requestPermission()
      // .then(() => {
      //   console.log("Notification")
      // })
      // .catch((err) => {
      //   console.log('Unable to get permission to notify.', err);
      // });
  }

  render() {
    this.notifications();
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
