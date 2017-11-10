import React from 'react';
import { StyleSheet, Text, View, TextInput, Button, Linking } from 'react-native';

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

export class LogIn extends React.Component {

  constructor(props){
    super(props);
    this.state = {username: ""}
  }

  render() {
    return (
      <View style={styles.container}>
        <View style={{flexDirection: 'column', flex: 0.2, width: '80%'}}>
          <TextInput style = {{flex: 0.8, height: 40}} onChangeText={(username) => this.setState({username})}/>
          <TextInput style = {{flex: 0.8, height: 40}} onChangeText={(password) => this.setState({password})}/>
          <Button title="LOG IN" onPress={ () => {
              Linking.openURL(`mailto:butiri.cristian@gmail.com?subject=Auto-generated%20mail&body=Hello%20${this.state.username}%20and%20welcome%20to%20the%20Booky%20app`);
              return this.props.navigation.navigate('BookList');
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
