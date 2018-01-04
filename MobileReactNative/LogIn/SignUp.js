import React from 'react';
import { StyleSheet, Text, View, TextInput, Button, Linking, Switch } from 'react-native';

class Anchor extends React.Component {
  _handlePress = () => {

    this.props.onPress && this.props.onPress();
  };

  render() {
    return (
      <Button title={this.props.title} onPress={this._handlePress} />
    );
  }
}

export class SignUp extends React.Component {

  constructor(props){
    super(props);
    this.state = {username: "", password: "", name: "", premium: false}
    this.dbRef = global.firebaseApp.database().ref().child('users');
    this.auth = global.firebaseApp.auth();
    this.auth.onAuthStateChanged((user) => {
      if(user){
        this.dbRef.child(user.uid).set({
            books: [], 
            email: this.state.username, 
            name: this.state.name, 
            password: this.state.password, 
            premium: this.state.premium
        })
      }
    })
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={{flexDirection: 'column', flex: 0.2, width: '80%'}}>
            <View style={{flexDirection: 'row'}}>
                <Text>Name</Text>
                <TextInput style={{width: '85%'}} onChangeText={(name) => this.setState({name})}/>
            </View>
            <View style={{flexDirection: 'row'}}>
                <Text>Email</Text>
                <TextInput style={{width: '85%'}} onChangeText={(username) => this.setState({username})}/>
            </View>
            <View style={{flexDirection: 'row'}}>
                <Text>Password</Text>
                <TextInput style={{width: '80%'}} onChangeText={(password) => this.setState({password})}/>
            </View>
            <View style={{flexDirection: 'row'}}>
                <Text>Premium</Text>
                <Switch value={this.state.premium} 
                onValueChange={(isPremium) => this.setState({premium: isPremium}) }/>
            </View>
            <Button title="REGISTER" onPress={ () => {
                this.auth.createUserWithEmailAndPassword(this.state.username, this.state.password);
            }}
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
