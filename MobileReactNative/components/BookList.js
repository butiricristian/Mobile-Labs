import React from 'react';
import { StyleSheet, Text, View, TextInput } from 'react-native';
import { StackNavigator } from 'react-navigation';

export default class BookList extends React.Component {

  constructor(){
    super();
    const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1!=r2});
    this.state.dataSource = ds.cloneWithRows(['row 1', 'row 2']);
  }

  render() {
    return (
      <View style={styles.container}>
        <Text>Book1</Text>
        <ListView
          dataSource={this.state.dataSource}
          renderRow={(rowData) => <Button onPress={() => this.props.navigation.navigate('BookDetails', {user: rowData.user})} title={rowData.user} />}
        />
      </View>
    );
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
