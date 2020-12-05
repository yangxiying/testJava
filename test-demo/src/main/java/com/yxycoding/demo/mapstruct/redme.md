# mapstruct 

参考：[丢掉那些BeanUtils工具类吧，MapStruct真香](https://mp.weixin.qq.com/s/z7esaXjX8fuYBnkZ41TLTg)

> 在复制对象值上有性能上的优势,其是在编译时，生成对象的之前的getter、setter实例。

- 例：如测试时的生在的class，是其自己生成的。
```java
public class PersonConverterImpl implements PersonConverter {
    public PersonConverterImpl() {
    }

    public PersonDTO do2dto(PersonDO person) {
        if (person == null) {
            return null;
        } else {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setUserName(person.getName());
            personDTO.setAge(person.getAge());
            personDTO.setBirthday(person.getBirthday());
            if (person.getGender() != null) {
                personDTO.setGender((Gender)Enum.valueOf(Gender.class, person.getGender()));
            }

            return personDTO;
        }
    }
}
```

> lombok 和 mapstruct 一起使用时
pom.xml中的bulid也必须配置lombok，要不在编译时，mapstruct先于lombok，会报错无 相关属性

```xml

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.1</version>
				<configuration>
					<source>1.8</source> <!-- depending on your project -->
					<target>1.8</target> <!-- depending on your project -->
					<annotationProcessorPaths>
						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
							<version>${lombok.version}</version>
						</path>
						<path>
							<groupId>org.mapstruct</groupId>
							<artifactId>mapstruct-processor</artifactId>
							<version>${org.mapstruct.version}</version>
						</path>
						<!-- other annotation processors -->
					</annotationProcessorPaths>
				</configuration>

			</plugin>
		</plugins>
	</build>

```