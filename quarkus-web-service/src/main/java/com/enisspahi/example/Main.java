package com.enisspahi.example;

import com.enisspahi.example.api.RestResourceRoot;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath(RestResourceRoot.APPLICATION_PATH)
public class Main extends Application {

}
