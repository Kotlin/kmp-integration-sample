//
//  ContentView.swift
//  SimpleLoginIOS
//
//  Created by Ekaterina.Petrova on 15.01.2021.
//

import SwiftUI
import shared

struct ContentView: View {
    
    var body: some View {
        Text(Greeting().greeting())
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
