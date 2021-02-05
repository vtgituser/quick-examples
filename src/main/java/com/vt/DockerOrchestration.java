package com.vt;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;
import java.util.List;

public class DockerOrchestration {
  public static void main(String[] args) {
    DockerClient dockerClient = DockerClientBuilder.getInstance().build();
    List<Container> containers = dockerClient.listContainersCmd().exec();

    containers.forEach(System.out::println);
  }
}
