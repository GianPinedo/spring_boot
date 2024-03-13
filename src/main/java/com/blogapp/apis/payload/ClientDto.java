package com.blogapp.apis.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDto {
        
        private Integer clientId;
        
        @NotEmpty
        @Size(min=2, max=100)
        private String clientName;
        
        @NotEmpty
        @Size(min=5, max=100, message="Email must be between 5 to 100 characters")
        private String clientEmail;
    
}
