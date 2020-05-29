package cn.trans88.kurotoll;

import java.util.List;

public class TestEntity {

    /**
     * type : commandXixunPlayer
     * command : {"_type":"PlayXixunTask","id":123,"task":{"_id":123,"name":"emergency","_department":null,"items":[{"_id":123,"_program":{"_id":"test","totalSize":0,"name":"应急节目","width":1280,"height":800,"_company":null,"_department":null,"_role":null,"_user":null,"__v":0,"layers":[{"repeat":true,"sources":[{"id":"","name":"text-program","sInterval":0,"sUrl":"","exitEffectTimeSpan":0,"entryEffectTimeSpan":0,"exitEffect":"None","entryEffect":"None","height":750,"width":1280,"top":50,"left":0,"playTime":0,"timeSpan":100000,"html":"<p style=\"font-size: 60px;\"> <\/p>","center":false,"lineHeight":1.4,"speed":10,"_type":"MultiLineText","backgroundColor":"rgba(0,0,0,1)"}]}],"dateCreated":"2020-5-29 09:55:12"},"priority":1,"repeatTimes":10,"version":0,"schedules":[]}],"executeDate":null,"cmdId":123}}
     */

    private String type;
    private CommandBean command;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CommandBean getCommand() {
        return command;
    }

    public void setCommand(CommandBean command) {
        this.command = command;
    }

    public static class CommandBean {
        /**
         * _type : PlayXixunTask
         * id : 123
         * task : {"_id":123,"name":"emergency","_department":null,"items":[{"_id":123,"_program":{"_id":"test","totalSize":0,"name":"应急节目","width":1280,"height":800,"_company":null,"_department":null,"_role":null,"_user":null,"__v":0,"layers":[{"repeat":true,"sources":[{"id":"","name":"text-program","sInterval":0,"sUrl":"","exitEffectTimeSpan":0,"entryEffectTimeSpan":0,"exitEffect":"None","entryEffect":"None","height":750,"width":1280,"top":50,"left":0,"playTime":0,"timeSpan":100000,"html":"<p style=\"font-size: 60px;\"> <\/p>","center":false,"lineHeight":1.4,"speed":10,"_type":"MultiLineText","backgroundColor":"rgba(0,0,0,1)"}]}],"dateCreated":"2020-5-29 09:55:12"},"priority":1,"repeatTimes":10,"version":0,"schedules":[]}],"executeDate":null,"cmdId":123}
         */

        private String _type;
        private int id;
        private TaskBean task;

        public String get_type() {
            return _type;
        }

        public void set_type(String _type) {
            this._type = _type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public static class TaskBean {
            /**
             * _id : 123
             * name : emergency
             * _department : null
             * items : [{"_id":123,"_program":{"_id":"test","totalSize":0,"name":"应急节目","width":1280,"height":800,"_company":null,"_department":null,"_role":null,"_user":null,"__v":0,"layers":[{"repeat":true,"sources":[{"id":"","name":"text-program","sInterval":0,"sUrl":"","exitEffectTimeSpan":0,"entryEffectTimeSpan":0,"exitEffect":"None","entryEffect":"None","height":750,"width":1280,"top":50,"left":0,"playTime":0,"timeSpan":100000,"html":"<p style=\"font-size: 60px;\"> <\/p>","center":false,"lineHeight":1.4,"speed":10,"_type":"MultiLineText","backgroundColor":"rgba(0,0,0,1)"}]}],"dateCreated":"2020-5-29 09:55:12"},"priority":1,"repeatTimes":10,"version":0,"schedules":[]}]
             * executeDate : null
             * cmdId : 123
             */

            private int _id;
            private String name;
            private Object _department;
            private Object executeDate;
            private int cmdId;
            private List<ItemsBean> items;

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
                this._id = _id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object get_department() {
                return _department;
            }

            public void set_department(Object _department) {
                this._department = _department;
            }

            public Object getExecuteDate() {
                return executeDate;
            }

            public void setExecuteDate(Object executeDate) {
                this.executeDate = executeDate;
            }

            public int getCmdId() {
                return cmdId;
            }

            public void setCmdId(int cmdId) {
                this.cmdId = cmdId;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * _id : 123
                 * _program : {"_id":"test","totalSize":0,"name":"应急节目","width":1280,"height":800,"_company":null,"_department":null,"_role":null,"_user":null,"__v":0,"layers":[{"repeat":true,"sources":[{"id":"","name":"text-program","sInterval":0,"sUrl":"","exitEffectTimeSpan":0,"entryEffectTimeSpan":0,"exitEffect":"None","entryEffect":"None","height":750,"width":1280,"top":50,"left":0,"playTime":0,"timeSpan":100000,"html":"<p style=\"font-size: 60px;\"> <\/p>","center":false,"lineHeight":1.4,"speed":10,"_type":"MultiLineText","backgroundColor":"rgba(0,0,0,1)"}]}],"dateCreated":"2020-5-29 09:55:12"}
                 * priority : 1
                 * repeatTimes : 10
                 * version : 0
                 * schedules : []
                 */

                private int _id;
                private ProgramBean _program;
                private int priority;
                private int repeatTimes;
                private int version;
                private List<?> schedules;

                public int get_id() {
                    return _id;
                }

                public void set_id(int _id) {
                    this._id = _id;
                }

                public ProgramBean get_program() {
                    return _program;
                }

                public void set_program(ProgramBean _program) {
                    this._program = _program;
                }

                public int getPriority() {
                    return priority;
                }

                public void setPriority(int priority) {
                    this.priority = priority;
                }

                public int getRepeatTimes() {
                    return repeatTimes;
                }

                public void setRepeatTimes(int repeatTimes) {
                    this.repeatTimes = repeatTimes;
                }

                public int getVersion() {
                    return version;
                }

                public void setVersion(int version) {
                    this.version = version;
                }

                public List<?> getSchedules() {
                    return schedules;
                }

                public void setSchedules(List<?> schedules) {
                    this.schedules = schedules;
                }

                public static class ProgramBean {
                    /**
                     * _id : test
                     * totalSize : 0
                     * name : 应急节目
                     * width : 1280
                     * height : 800
                     * _company : null
                     * _department : null
                     * _role : null
                     * _user : null
                     * __v : 0
                     * layers : [{"repeat":true,"sources":[{"id":"","name":"text-program","sInterval":0,"sUrl":"","exitEffectTimeSpan":0,"entryEffectTimeSpan":0,"exitEffect":"None","entryEffect":"None","height":750,"width":1280,"top":50,"left":0,"playTime":0,"timeSpan":100000,"html":"<p style=\"font-size: 60px;\"> <\/p>","center":false,"lineHeight":1.4,"speed":10,"_type":"MultiLineText","backgroundColor":"rgba(0,0,0,1)"}]}]
                     * dateCreated : 2020-5-29 09:55:12
                     */

                    private String _id;
                    private int totalSize;
                    private String name;
                    private int width;
                    private int height;
                    private Object _company;
                    private Object _department;
                    private Object _role;
                    private Object _user;
                    private int __v;
                    private String dateCreated;
                    private List<LayersBean> layers;

                    public String get_id() {
                        return _id;
                    }

                    public void set_id(String _id) {
                        this._id = _id;
                    }

                    public int getTotalSize() {
                        return totalSize;
                    }

                    public void setTotalSize(int totalSize) {
                        this.totalSize = totalSize;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public Object get_company() {
                        return _company;
                    }

                    public void set_company(Object _company) {
                        this._company = _company;
                    }

                    public Object get_department() {
                        return _department;
                    }

                    public void set_department(Object _department) {
                        this._department = _department;
                    }

                    public Object get_role() {
                        return _role;
                    }

                    public void set_role(Object _role) {
                        this._role = _role;
                    }

                    public Object get_user() {
                        return _user;
                    }

                    public void set_user(Object _user) {
                        this._user = _user;
                    }

                    public int get__v() {
                        return __v;
                    }

                    public void set__v(int __v) {
                        this.__v = __v;
                    }

                    public String getDateCreated() {
                        return dateCreated;
                    }

                    public void setDateCreated(String dateCreated) {
                        this.dateCreated = dateCreated;
                    }

                    public List<LayersBean> getLayers() {
                        return layers;
                    }

                    public void setLayers(List<LayersBean> layers) {
                        this.layers = layers;
                    }

                    public static class LayersBean {
                        /**
                         * repeat : true
                         * sources : [{"id":"","name":"text-program","sInterval":0,"sUrl":"","exitEffectTimeSpan":0,"entryEffectTimeSpan":0,"exitEffect":"None","entryEffect":"None","height":750,"width":1280,"top":50,"left":0,"playTime":0,"timeSpan":100000,"html":"<p style=\"font-size: 60px;\"> <\/p>","center":false,"lineHeight":1.4,"speed":10,"_type":"MultiLineText","backgroundColor":"rgba(0,0,0,1)"}]
                         */

                        private boolean repeat;
                        private List<SourcesBean> sources;

                        public boolean isRepeat() {
                            return repeat;
                        }

                        public void setRepeat(boolean repeat) {
                            this.repeat = repeat;
                        }

                        public List<SourcesBean> getSources() {
                            return sources;
                        }

                        public void setSources(List<SourcesBean> sources) {
                            this.sources = sources;
                        }

                        public static class SourcesBean {
                            /**
                             * id :
                             * name : text-program
                             * sInterval : 0
                             * sUrl :
                             * exitEffectTimeSpan : 0
                             * entryEffectTimeSpan : 0
                             * exitEffect : None
                             * entryEffect : None
                             * height : 750
                             * width : 1280
                             * top : 50
                             * left : 0
                             * playTime : 0
                             * timeSpan : 100000
                             * html : <p style="font-size: 60px;"> </p>
                             * center : false
                             * lineHeight : 1.4
                             * speed : 10
                             * _type : MultiLineText
                             * backgroundColor : rgba(0,0,0,1)
                             */

                            private String id;
                            private String name;
                            private int sInterval;
                            private String sUrl;
                            private int exitEffectTimeSpan;
                            private int entryEffectTimeSpan;
                            private String exitEffect;
                            private String entryEffect;
                            private int height;
                            private int width;
                            private int top;
                            private int left;
                            private int playTime;
                            private int timeSpan;
                            private String html;
                            private boolean center;
                            private double lineHeight;
                            private int speed;
                            private String _type;
                            private String backgroundColor;

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public int getSInterval() {
                                return sInterval;
                            }

                            public void setSInterval(int sInterval) {
                                this.sInterval = sInterval;
                            }

                            public String getSUrl() {
                                return sUrl;
                            }

                            public void setSUrl(String sUrl) {
                                this.sUrl = sUrl;
                            }

                            public int getExitEffectTimeSpan() {
                                return exitEffectTimeSpan;
                            }

                            public void setExitEffectTimeSpan(int exitEffectTimeSpan) {
                                this.exitEffectTimeSpan = exitEffectTimeSpan;
                            }

                            public int getEntryEffectTimeSpan() {
                                return entryEffectTimeSpan;
                            }

                            public void setEntryEffectTimeSpan(int entryEffectTimeSpan) {
                                this.entryEffectTimeSpan = entryEffectTimeSpan;
                            }

                            public String getExitEffect() {
                                return exitEffect;
                            }

                            public void setExitEffect(String exitEffect) {
                                this.exitEffect = exitEffect;
                            }

                            public String getEntryEffect() {
                                return entryEffect;
                            }

                            public void setEntryEffect(String entryEffect) {
                                this.entryEffect = entryEffect;
                            }

                            public int getHeight() {
                                return height;
                            }

                            public void setHeight(int height) {
                                this.height = height;
                            }

                            public int getWidth() {
                                return width;
                            }

                            public void setWidth(int width) {
                                this.width = width;
                            }

                            public int getTop() {
                                return top;
                            }

                            public void setTop(int top) {
                                this.top = top;
                            }

                            public int getLeft() {
                                return left;
                            }

                            public void setLeft(int left) {
                                this.left = left;
                            }

                            public int getPlayTime() {
                                return playTime;
                            }

                            public void setPlayTime(int playTime) {
                                this.playTime = playTime;
                            }

                            public int getTimeSpan() {
                                return timeSpan;
                            }

                            public void setTimeSpan(int timeSpan) {
                                this.timeSpan = timeSpan;
                            }

                            public String getHtml() {
                                return html;
                            }

                            public void setHtml(String html) {
                                this.html = html;
                            }

                            public boolean isCenter() {
                                return center;
                            }

                            public void setCenter(boolean center) {
                                this.center = center;
                            }

                            public double getLineHeight() {
                                return lineHeight;
                            }

                            public void setLineHeight(double lineHeight) {
                                this.lineHeight = lineHeight;
                            }

                            public int getSpeed() {
                                return speed;
                            }

                            public void setSpeed(int speed) {
                                this.speed = speed;
                            }

                            public String get_type() {
                                return _type;
                            }

                            public void set_type(String _type) {
                                this._type = _type;
                            }

                            public String getBackgroundColor() {
                                return backgroundColor;
                            }

                            public void setBackgroundColor(String backgroundColor) {
                                this.backgroundColor = backgroundColor;
                            }
                        }
                    }
                }
            }
        }
    }
}
