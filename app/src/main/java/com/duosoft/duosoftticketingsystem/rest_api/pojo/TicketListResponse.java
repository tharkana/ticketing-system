package com.duosoft.duosoftticketingsystem.rest_api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tharkana on 6/25/2017.
 */


/**
 * {
 * "Result": [{
 * "_id": "5943ad20dd9a640001638c80",
 * "type": "Inquery",
 * "subject": "Account balance",
 * "description": "Customer called to find out account balance",
 * "status": "new",
 * "priority": "normal"
 * }]
 * }
 */

public class TicketListResponse {

    @SerializedName("Result")
    @Expose
    private List<Ticket> result = null;

    public List<Ticket> getResult() {
        return result;
    }

    public void setResult(List<Ticket> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TicketListResponse{" +
                "result=" + result +
                '}';
    }

    public class Ticket {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("subject")
        @Expose
        private String subject;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("priority")
        @Expose
        private String priority;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Ticket{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", subject='" + subject + '\'' +
                    ", description='" + description + '\'' +
                    ", status='" + status + '\'' +
                    ", priority='" + priority + '\'' +
                    '}';
        }
    }
}
