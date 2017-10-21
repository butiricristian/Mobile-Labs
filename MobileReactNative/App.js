import React from 'react';
import { StyleSheet, Text, View, TextInput } from 'react-native';

export default class App extends React.Component {

  render() {
    return (
      <View style={styles.container}>
        <Text></Text>
        <View style={{flexDirection: 'row'}}>
          <TextInput style = {{flex: 0.8, height: 40}}/>
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
